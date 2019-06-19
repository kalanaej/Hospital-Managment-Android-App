package com.abc.testapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "hospital_database";
    private static final int DATABASE_VERSION = 4;

    //Doctor table
    private static final String TABLE_DOCTOR = "doctors";
    private static final String KEY_DID = "id";
    private static final String KEY_DNAME = "name";
    private static final String KEY_DGENDER = "gender";
    private static final String KEY_DSPECIAL = "special";
    private static final String KEY_DADDRESS = "address";
    private static final String KEY_DAGE = "age";
    private static final String KEY_DPHONE = "phone";
    private static final String KEY_DWARD = "ward";


    //Patient table
    private static final String TABLE_USER = "patient";
    private static final String P_id = "id";
    private static final String P_NAME = "name";
    private static final String P_AGE = "age";
    private static final String P_ADDRESS = "address";
    private static final String P_CONTACTNO= "contNo";
    private static final String P_DISEASE= "disease";
    private static final String P_GENDER= "gender";
    private static final String P_GURNAME= "gurName";


    //drugs table
    private static final String TABLE_DRUGS = "drugs";
    private static final String KEY_DRUGID = "id";
    private static final String KEY_DRUGNAME = "name";
    private static final String KEY_MANUFACTURER = "manufacturer";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_PRICE = "price";
    private static final String KEY_DESCRIPTION = "description";


    //staff table
    private static final String TABLE_STAFF1 = "staff4";
    private static final String S_KEY_ID = "id";
    private static final String S_KEY_FIRSTNAME = "name";
    private static final String S_KEY_ADDRESS = "address";
    private static final String S_KEY_DEPARTMENT = "department";
    private static final String S_KEY_AGE = "age";
    private static final String S_KEY_TP = "tp";
    private static final String S_KEY_GENDER = "gen";


    //CREATE TABLE doctors
    private static final String CREATE_TABLE_DOCTOR = "CREATE TABLE "+ TABLE_DOCTOR +"(" + KEY_DID  + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ KEY_DNAME +" TEXT, "+ KEY_DAGE +" TEXT, "+ KEY_DGENDER +" TEXT, "+ KEY_DSPECIAL +" TEXT, "+ KEY_DPHONE +" TEXT, "+ KEY_DADDRESS +" TEXT, "+ KEY_DWARD +" TEXT );";

    //CREATE TABLE patient
    private static final String CREATE_TABLE_PATIENT = "CREATE TABLE " + TABLE_USER + "(" + P_id + " INTEGER PRIMARY KEY AUTOINCREMENT," + P_NAME + " TEXT, "+ P_AGE + " TEXT,"+ P_ADDRESS+ "  TEXT,"+ P_CONTACTNO+ "  TEXT,"+ P_DISEASE+ "  TEXT,"+ P_GENDER+ "  TEXT,"+ P_GURNAME+ "  TEXT);";

    //CREATE TABLE drugs
    private static final String CREATE_TABLE_DRUGS = "CREATE TABLE " + TABLE_DRUGS + "(" + KEY_DRUGID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_DRUGNAME + " TEXT, "+ KEY_MANUFACTURER + " TEXT, "+ KEY_QUANTITY + " TEXT, "+ KEY_PRICE + " TEXT, "+ KEY_DESCRIPTION + " TEXT );";

    //CREATE TABLE staff4
    private static final String CREATE_TABLE_STAFF1 = "CREATE TABLE " + TABLE_STAFF1 + "(" + S_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + S_KEY_FIRSTNAME + " TEXT, " + S_KEY_AGE + " TEXT ,  " + S_KEY_GENDER + " TEXT,   "+ S_KEY_ADDRESS + " TEXT ,   " + S_KEY_TP +" TEXT ,   " + S_KEY_DEPARTMENT +" TEXT   );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        /*Log.d("table", CREATE_TABLE_DOCTOR);
        Log.d("table", CREATE_TABLE_PATIENT);
        Log.d("table", CREATE_TABLE_DRUGS);*/
        Log.d("table", CREATE_TABLE_STAFF1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_DOCTOR);
        db.execSQL(CREATE_TABLE_PATIENT);
        db.execSQL(CREATE_TABLE_DRUGS);
        db.execSQL(CREATE_TABLE_STAFF1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_DOCTOR + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_USER + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_DRUGS + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_STAFF1 + "'");
        onCreate(db);
    }

    //add
    public long addDoctorDetail(String name, String age, String gender, String special, String phone, String address, String ward) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();

        values.put(KEY_DNAME, name);
        values.put(KEY_DAGE, age);
        values.put(KEY_DGENDER, gender);
        values.put(KEY_DSPECIAL, special);
        values.put(KEY_DPHONE, phone);
        values.put(KEY_DADDRESS, address);
        values.put(KEY_DWARD, ward);

        // insert row in doctors table
        long insert = db.insert(TABLE_DOCTOR, null, values);

        return insert;
    }

    //list
    public ArrayList<DoctorModel> getAllDoctors() {

        ArrayList<DoctorModel> doctorModelArrayList = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TABLE_DOCTOR, null);

        if (c.moveToFirst()) {

            doctorModelArrayList = new ArrayList<DoctorModel>();

            do {
                DoctorModel doctorModel = new DoctorModel();

                doctorModel.setId(c.getInt(c.getColumnIndex(KEY_DID)));
                doctorModel.setName(c.getString(c.getColumnIndex(KEY_DNAME)));
                doctorModel.setAge(c.getString(c.getColumnIndex(KEY_DAGE)));
                doctorModel.setGender(c.getString(c.getColumnIndex(KEY_DGENDER)));
                doctorModel.setSpecial(c.getString(c.getColumnIndex(KEY_DSPECIAL)));
                doctorModel.setPhone(c.getString(c.getColumnIndex(KEY_DPHONE)));
                doctorModel.setAddress(c.getString(c.getColumnIndex(KEY_DADDRESS)));
                doctorModel.setWard(c.getString(c.getColumnIndex(KEY_DWARD)));

                // adding to doctors list
                doctorModelArrayList.add(doctorModel);
            } while (c.moveToNext());
        }
        return doctorModelArrayList;
    }

    //update
    public int updateDoctor(int id, String name, String age, String gender, String special, String phone, String address, String ward) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_DNAME, name);
        values.put(KEY_DAGE, age);
        values.put(KEY_DGENDER, gender);
        values.put(KEY_DSPECIAL, special);
        values.put(KEY_DPHONE, phone);
        values.put(KEY_DADDRESS, address);
        values.put(KEY_DWARD, ward);

        // update row in doctors table base on doctors id
        return db.update(TABLE_DOCTOR, values, KEY_DID + " = ?", new String[]{String.valueOf(id)});
    }

    //delete
    public void deleteDoctor(int id) {

        // delete row in doctors table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DOCTOR, KEY_DID + " = ?", new String[]{String.valueOf(id)});
    }

    //Search
    public ArrayList<DoctorModel> search(String key) {

        ArrayList<DoctorModel> doctorModels = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT  * FROM " + TABLE_DOCTOR + " where " + KEY_DNAME + " LIKE ?", new String[] { "%" + key + "%" });

            if (c.moveToFirst()) {

                doctorModels = new ArrayList<DoctorModel>();

                do {

                    DoctorModel doctorModel = new DoctorModel();

                    doctorModel.setId(c.getInt(c.getColumnIndex(KEY_DID)));
                    doctorModel.setName(c.getString(c.getColumnIndex(KEY_DNAME)));
                    doctorModel.setAge(c.getString(c.getColumnIndex(KEY_DAGE)));
                    doctorModel.setGender(c.getString(c.getColumnIndex(KEY_DGENDER)));
                    doctorModel.setSpecial(c.getString(c.getColumnIndex(KEY_DSPECIAL)));
                    doctorModel.setPhone(c.getString(c.getColumnIndex(KEY_DPHONE)));
                    doctorModel.setAddress(c.getString(c.getColumnIndex(KEY_DADDRESS)));
                    doctorModel.setWard(c.getString(c.getColumnIndex(KEY_DWARD)));

                    // adding to doctors list
                    doctorModels.add(doctorModel);
                } while (c.moveToNext());
            }
        } catch (Exception e) {
            doctorModels = null;
        }
        return doctorModels;
    }

    /*






                Patient Table Add, Update, Display, Delete, Search








     */



    public long addPatientDetail(String name, String age, String address, String contNo, String disease, String gender, String gurName ) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(P_NAME, name);
        values.put(P_AGE, age);
        values.put(P_ADDRESS, address);
        values.put(P_CONTACTNO, contNo);
        values.put(P_DISEASE, disease);
        values.put(P_GENDER, gender);
        values.put(P_GURNAME, gurName);
        // insert row in patient table
        long insert = db.insert(TABLE_USER, null, values);

        return insert;
    }

    public ArrayList<PatientModel> getAllUsers() {
//        ArrayList<PatientModel> patientModelArrayList = new ArrayList<PatientModel>();

        ArrayList<PatientModel> patientModelArrayList =null;


        SQLiteDatabase db = getReadableDatabase();
        Cursor c=db.rawQuery("select * from " +TABLE_USER,null);

//        String selectQuery = "SELECT  * FROM " + TABLE_USER;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            patientModelArrayList=new  ArrayList<PatientModel>();

            do {
                PatientModel patientModel = new PatientModel();
                patientModel.setId(c.getInt(c.getColumnIndex(P_id)));
                patientModel.setName(c.getString(c.getColumnIndex(P_NAME)));
                patientModel.setAge(c.getString(c.getColumnIndex(P_AGE)));
                patientModel.setAddress(c.getString(c.getColumnIndex(P_ADDRESS)));
                patientModel.setContNo(c.getString(c.getColumnIndex(P_CONTACTNO)));
                patientModel.setDisease(c.getString(c.getColumnIndex(P_DISEASE)));
                patientModel.setGender(c.getString(c.getColumnIndex(P_GENDER)));
                patientModel.setGurName(c.getString(c.getColumnIndex(P_GURNAME)));
                // adding to Patients list
                patientModelArrayList.add(patientModel);
            } while (c.moveToNext());
        }
        return patientModelArrayList;
    }

    public int updatePatient(int id, String name, String age, String address, String contNo, String disease, String gender, String guardian) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(P_NAME, name);
        values.put(P_AGE, age);
        values.put(P_ADDRESS,address);
        values.put(P_CONTACTNO,contNo);
        values.put(P_DISEASE,disease);
        values.put(P_GENDER,gender);
        values.put(P_GURNAME, guardian);

        // update row in patient table base on patient value
        return db.update(TABLE_USER, values, P_id + " = ?",
                new String[]{String.valueOf(id)});
    }
    public void deletePatient(int id) {

        // delete row in patient table  id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, P_id + " = ?",
                new String[]{String.valueOf(id)});
    }
    public ArrayList<PatientModel> searchPatients(String key) { //Search details patients

        ArrayList<PatientModel> patient =null;

        try {

            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("select * from " + TABLE_USER + " where " + P_NAME + " LIKE ?", new String[] {"%" +key+ "%"});


            if (c.moveToFirst()) {
                patient = new ArrayList<PatientModel>();

                do {
                    PatientModel patientModel = new PatientModel();
                    patientModel.setId(c.getInt(c.getColumnIndex(P_id)));
                    patientModel.setName(c.getString(c.getColumnIndex(P_NAME)));
                    patientModel.setAge(c.getString(c.getColumnIndex(P_AGE)));
                    patientModel.setAddress(c.getString(c.getColumnIndex(P_ADDRESS)));
                    patientModel.setContNo(c.getString(c.getColumnIndex(P_CONTACTNO)));
                    patientModel.setDisease(c.getString(c.getColumnIndex(P_DISEASE)));
                    patientModel.setGender(c.getString(c.getColumnIndex(P_GENDER)));
                    patientModel.setGurName(c.getString(c.getColumnIndex(P_GURNAME)));

                    // adding to Patients list
                    patient.add(patientModel);
                } while (c.moveToNext());
            }
        }catch(Exception e){
            patient=null;
        }
        return patient;
    }

/*





        Drugs Table Add, Update, Display, Delete, Search





*/

    public long addDrugsDetail(String name, String manufacturer,String quantity,String price,String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_DRUGNAME, name);
        values.put(KEY_MANUFACTURER,manufacturer);
        values.put(KEY_QUANTITY,quantity);
        values.put(KEY_PRICE,price);
        values.put(KEY_DESCRIPTION,description);
        // insert row in drugs table
        long insert = db.insert(TABLE_DRUGS, null, values);

        return insert;
    }


    public ArrayList<DrugsModel> getAllDrugs() {
        // ArrayList<DrugsModel> drugsModelArrayList = new ArrayList<DrugsModel>();
        ArrayList<DrugsModel> drugsModelArrayList = null;

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_DRUGS,null);



//        String selectQuery = "SELECT  * FROM " + TABLE_DRUGS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            drugsModelArrayList = new ArrayList<DrugsModel>();
            do {
                DrugsModel drugsModel = new DrugsModel();
                drugsModel.setId(c.getInt(c.getColumnIndex(KEY_DRUGID)));
                drugsModel.setName(c.getString(c.getColumnIndex(KEY_DRUGNAME)));
                drugsModel.setManufacturer(c.getString(c.getColumnIndex(KEY_MANUFACTURER)));
                drugsModel.setQuantity(c.getString(c.getColumnIndex(KEY_QUANTITY)));
                drugsModel.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                drugsModel.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                // adding to drugs list
                drugsModelArrayList.add(drugsModel);
            } while (c.moveToNext());
        }
        return drugsModelArrayList;
    }


    public int updateDrugs(int id,String name, String manufacturer,String quantity,String price,String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(KEY_DRUGNAME, name);
        values.put(KEY_MANUFACTURER,manufacturer);
        values.put(KEY_QUANTITY,quantity);
        values.put(KEY_PRICE,price);
        values.put(KEY_DESCRIPTION,description);
        // update row in drugs table base on drugs.is value
        return db.update(TABLE_DRUGS, values, KEY_DRUGID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteDrug(int id) {

        // delete row in drugs table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DRUGS, KEY_DRUGID + " = ?",
                new String[]{String.valueOf(id)});
    }


    public ArrayList<DrugsModel> searchDrugs(String drug) {
        // ArrayList<DrugsModel> drugsModelArrayList = new ArrayList<DrugsModel>();
        ArrayList<DrugsModel> drugsModels = null;

        try {

            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM " + TABLE_DRUGS + " WHERE " + KEY_DRUGNAME + " LIKE ?", new String[] { "%" + drug + "%" });


//        String selectQuery = "SELECT  * FROM " + TABLE_DRUGS;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
            // looping through all rows and adding to list
            if (c.moveToFirst()) {
                drugsModels = new ArrayList<DrugsModel>();
                do {
                    DrugsModel drugsModel = new DrugsModel();
                    drugsModel.setId(c.getInt(c.getColumnIndex(KEY_DRUGID)));
                    drugsModel.setName(c.getString(c.getColumnIndex(KEY_DRUGNAME)));
                    drugsModel.setManufacturer(c.getString(c.getColumnIndex(KEY_MANUFACTURER)));
                    drugsModel.setQuantity(c.getString(c.getColumnIndex(KEY_QUANTITY)));
                    drugsModel.setPrice(c.getString(c.getColumnIndex(KEY_PRICE)));
                    drugsModel.setDescription(c.getString(c.getColumnIndex(KEY_DESCRIPTION)));
                    // adding to drugs list
                    drugsModels.add(drugsModel);
                } while (c.moveToNext());
            }
        }catch(Exception e) {
            drugsModels = null;
        }
        return drugsModels;
    }
/*






        Staff Table Add, Update, Display, Delete, Search







 */

    public long addStaffDetail(String name, String age , String gen,  String address, String tp, String department ) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Creating content values
        ContentValues values = new ContentValues();
        values.put(S_KEY_FIRSTNAME, name);
        values.put(S_KEY_AGE, age);
        values.put(S_KEY_GENDER, gen);
        values.put(S_KEY_ADDRESS, address);
        values.put(S_KEY_TP, tp);
        values.put(S_KEY_DEPARTMENT, department);


        // insert row in students table
        long insert = db.insert(TABLE_STAFF1, null, values);

        return insert;
    }

    public ArrayList<StaffModel> getAllStaff() {
        //ArrayList<StaffModel> staffModelArrayList = new ArrayList<StaffModel>();

        ArrayList<StaffModel> staffModelArrayList = null;

        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("select * from "+TABLE_STAFF1,null);


//        String selectQuery = "SELECT  * FROM " + TABLE_STAFF1;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
        if (c.moveToFirst()) {

            staffModelArrayList = new ArrayList<>();
            do {
                StaffModel staffModel = new StaffModel();
                staffModel.setId(c.getInt(c.getColumnIndex(S_KEY_ID)));
                staffModel.setName(c.getString(c.getColumnIndex(S_KEY_FIRSTNAME)));
                staffModel.setAge(c.getString(c.getColumnIndex(S_KEY_AGE)));
                staffModel.setGender(c.getString(c.getColumnIndex(S_KEY_GENDER)));
                staffModel.setAddress(c.getString(c.getColumnIndex(S_KEY_ADDRESS)));
                staffModel.setTp(c.getString(c.getColumnIndex(S_KEY_TP)));
                staffModel.setDepartment(c.getString(c.getColumnIndex(S_KEY_DEPARTMENT)));
                // adding to Students list
                staffModelArrayList.add(staffModel);
            } while (c.moveToNext());
        }
        return staffModelArrayList;
    }

    public int updateStaff(int id, String name, String age, String gen, String address, String tp, String department) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(S_KEY_FIRSTNAME, name);
        values.put(S_KEY_AGE, age);
        values.put(S_KEY_GENDER, gen);
        values.put(S_KEY_ADDRESS, address);
        values.put(S_KEY_TP, tp);
        values.put(S_KEY_DEPARTMENT, department);
        // update row in students table base on students.is value
        return db.update(TABLE_STAFF1, values, S_KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteStaff(int id) {

        // delete row in students table based on id
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STAFF1, S_KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }


    public ArrayList<StaffModel> searchStaff(String key) {
        //ArrayList<StaffModel> staffModelArrayList = new ArrayList<StaffModel>();



        ArrayList<StaffModel> staff = null;

        try {
            SQLiteDatabase db = getReadableDatabase();

            Cursor c = db.rawQuery("select * from " + TABLE_STAFF1 + " where " + S_KEY_FIRSTNAME + " LIKE ? ",new String[]{"%" + key+"%"}  );


//        String selectQuery = "SELECT  * FROM " + TABLE_STAFF1;
//        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor c = db.rawQuery(selectQuery, null);
//        // looping through all rows and adding to list
            if (c.moveToFirst()) {

                staff = new ArrayList<>();
                do {
                    StaffModel staffModel = new StaffModel();
                    staffModel.setId(c.getInt(c.getColumnIndex(S_KEY_ID)));
                    staffModel.setName(c.getString(c.getColumnIndex(S_KEY_FIRSTNAME)));
                    staffModel.setAge(c.getString(c.getColumnIndex(S_KEY_AGE)));
                    staffModel.setGender(c.getString(c.getColumnIndex(S_KEY_GENDER)));
                    staffModel.setAddress(c.getString(c.getColumnIndex(S_KEY_ADDRESS)));
                    staffModel.setTp(c.getString(c.getColumnIndex(S_KEY_TP)));
                    staffModel.setDepartment(c.getString(c.getColumnIndex(S_KEY_DEPARTMENT)));
                    // adding to Students list
                    staff.add(staffModel);
                } while (c.moveToNext());
            }
        }catch (Exception e){
            staff= null;
        }
        return staff;
    }
}
