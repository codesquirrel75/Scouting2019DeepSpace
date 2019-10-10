package com.example.vande.scouting2018.data;

import android.provider.BaseColumns;

public class TeamsContract {

    private TeamsContract(){}

    public static final class TeamEntry implements BaseColumns {

        //  constant variable with Table Name
        public static final String TABLE_NAME = "teams";

        // constant variables of column names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_TEAM_NUMBER = "team_number";
        public static final String COLUMN_TEAM_NAME = "team_name";


    }

}
