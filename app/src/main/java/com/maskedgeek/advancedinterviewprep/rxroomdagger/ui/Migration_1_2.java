package com.maskedgeek.advancedinterviewprep.rxroomdagger.ui;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migration_1_2 extends Migration {

    public Migration_1_2(int startversion,int endversion) {
        super(startversion, endversion);
    }
    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE addresses ADD COLUMN code INTEGER NOT NULL DEFAULT 0");
    }

}
