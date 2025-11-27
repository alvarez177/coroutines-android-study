package com.sazcode.coroutinas.data.localsource.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseMigrations {

    val MIGRATION_1_TO_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            val dragonBallCharactersQuery = """
                ALTER TABLE dragonballcharacterentity 
                ADD COLUMN is_synced INTEGER NOT NULL DEFAULT 0
                """.trimIndent()

            val rickAndMortyCharactersQuery = """
                ALTER TABLE rickandmortycharacterentity 
                ADD COLUMN is_synced INTEGER NOT NULL DEFAULT 0
                """.trimIndent()

            db.execSQL(dragonBallCharactersQuery)
            db.execSQL(rickAndMortyCharactersQuery)
        }
    }
}