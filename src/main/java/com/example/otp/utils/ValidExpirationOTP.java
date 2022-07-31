package com.example.otp.utils;

import java.util.Date;

public class ValidExpirationOTP {

    public static long findDifference(Date start_date, Date end_date) {
        try {
            long difference_In_Time = end_date.getTime() - start_date.getTime();

            long difference_In_Minutes = (difference_In_Time
                    / (1000 * 60))
                    % 60;
            return difference_In_Minutes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

}
