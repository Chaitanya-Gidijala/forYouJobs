package com.foryou.jobs.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateConverter {

    public static String convertDate(String inputDate) {
        // Parse the input date string into a LocalDate object
        LocalDate date = LocalDate.parse(inputDate);

        // Format the LocalDate object into the desired format
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));

        return formattedDate;
    }

    public static void main(String[] args) {
        String inputDate = "2024-03-25";
        String formattedDate = convertDate(inputDate);
        System.out.println(formattedDate); // Output: March 25, 2024
    }
}

