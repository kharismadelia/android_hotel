package com.example.acere5_475.last;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hotel.db";
    private static final String TABLE_DATA = "hotel";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAMA = "nama";
    private static final String COLUMN_ALAMAT = "alamat";
    private static final String COLUMN_HP = "hp";
    private static final String COLUMN_HOTEL = "hotel";
    private static final String COLUMN_TANGGAL = "tanggal";

    SQLiteDatabase db;

    public DBAdapter(Context context,String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {

        String CREATE_DATA_TABLE = "CREATE TABLE " +TABLE_DATA
                +"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_NAMA+" TEXT NOT NULL, "
                + COLUMN_ALAMAT+" TEXT NOT NULL, "
                + COLUMN_HP+" TEXT NOT NULL, "
                + COLUMN_HOTEL+" TEXT NOT NULL, "
                + COLUMN_TANGGAL+" TEXT NOT NULL"+")";
        db.execSQL(CREATE_DATA_TABLE);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_DATA);
        onCreate(db);
        Log.v("DBAdapter", "Upgrading database from version "+oldVersion
                +" to"+newVersion+" which will destroy all data");
    }

    public void onOpen(){
        super.onOpen(db);
        db = this.getWritableDatabase();
    }

    public synchronized void close(){
        super.close();
    }

    public void insertData(Hotel hotel){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMA, hotel.get_nama());
        contentValues.put(COLUMN_ALAMAT, hotel.get_alamat());
        contentValues.put(COLUMN_HP, hotel.get_hp());
        contentValues.put(COLUMN_HOTEL, hotel.get_hotel());
        contentValues.put(COLUMN_TANGGAL, hotel.get_tanggal());
        db.insert(TABLE_DATA, null, contentValues);
    }

    public Hotel getData(String nama){
        String query = "SELECT * FROM "+TABLE_DATA+" WHERE "
                +COLUMN_NAMA+" LIKE \"%"+nama+"%\"";
        Cursor cursor = db.rawQuery(query, null);
        Hotel hotel = new Hotel();
        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            hotel.set_id(Integer.parseInt(cursor.getString(0)));
            hotel.set_nama(cursor.getString(1));
            hotel.set_alamat(cursor.getString(2));
            hotel.set_hp(cursor.getString(3));
            hotel.set_hotel(cursor.getString(4));
            hotel.set_tanggal(cursor.getString(5));
            cursor.close();
        }
        else {
            hotel = null;
        }
        return hotel;
    }

    public Cursor getAllData(){
        Cursor cursor = db.query(TABLE_DATA, new String[]{COLUMN_ID, COLUMN_NAMA, COLUMN_ALAMAT, COLUMN_HP, COLUMN_HOTEL, COLUMN_TANGGAL},
                null, null, null, null, null);
        return cursor;
    }

    public int updateData(int rowID, String nama, String alamat, String hp, String hotel, String tanggal){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAMA, nama);
        contentValues.put(COLUMN_ALAMAT, alamat);
        contentValues.put(COLUMN_HP, hp);
        contentValues.put(COLUMN_HOTEL, hotel);
        contentValues.put(COLUMN_TANGGAL, tanggal);
        String[] whereArgs = {String.valueOf(rowID)};
        int id = db.update(TABLE_DATA, contentValues, COLUMN_ID+" = ?", whereArgs);
        return id;
    }

    public  int deleteData(int id){
        int jumlah = db.delete(TABLE_DATA, COLUMN_ID+" =?",
                new String[]{String.valueOf(id)});
        return jumlah;
    }

}