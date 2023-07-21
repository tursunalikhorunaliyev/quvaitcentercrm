package com.itcentercrmquva.quvaitcentercrm.service;

import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;

@Service
public class StringToDateCheckerService {
    public boolean isDateAvailable(String date){
        String[] dateArray = date.split("-");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);

        try{
            LocalDate localDate = LocalDate.of(year, month, day);
            return false;
        }
        catch (DateTimeException e){
            return true;
        }
    }
    public LocalDate generateLocalDate(String date){
        String[] dateArray = date.split("-");
        int day = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int year = Integer.parseInt(dateArray[2]);
        return LocalDate.of(year, month, day);
    }
}
