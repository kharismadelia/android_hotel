package com.example.acere5_475.last;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Form extends Fragment {
    View view;
    EditText editID, editNama, editAlamat, editHP, editHotel, editTanggal;
    Button btnAdd, btnGet, btnGetAll, btnUpdate, btnDelete, btnClear;

    public Form() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_form, container, false);
        return view;

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editID = (EditText) view.findViewById(R.id.inputID);
        editNama = (EditText) view.findViewById(R.id.inputNama);
        editAlamat = (EditText) view.findViewById(R.id.inputAlamat);
        editHP = (EditText) view.findViewById(R.id.inputHP);
        editHotel = (EditText) view.findViewById(R.id.inputHotel);
        editTanggal = (EditText) view.findViewById(R.id.inputTanggal);

        btnAdd = (Button) view.findViewById(R.id.btnAdd);
        btnGet = (Button) view.findViewById(R.id.btnGet); //SEARCH
        btnGetAll = (Button) view.findViewById(R.id.btnGetAll);
        btnUpdate = (Button) view.findViewById(R.id.btnUpdate);
        btnDelete = (Button) view.findViewById(R.id.btnDelete);
        btnClear = (Button) view.findViewById(R.id.btnClear); //RESET

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter dbAdapter = new DBAdapter(getActivity(), null, null, 1);
                String nama = editNama.getText().toString();
                String alamat = editAlamat.getText().toString();
                String hp = editHP.getText().toString();
                String hotel = editHotel.getText().toString();
                String tanggal = editTanggal.getText().toString();

                if (nama.isEmpty() || alamat.isEmpty() || hp.isEmpty() || hotel.isEmpty() || tanggal.isEmpty()) {
                    displayToast("Maaf FORM harus diisi");
                } else {
                    Hotel data = new Hotel(nama, alamat, hp, hotel, tanggal);
                    dbAdapter.onOpen();
                    dbAdapter.insertData(data);
                    dbAdapter.close();

                    editNama.setText("");
                    editAlamat.setText("");
                    editHP.setText("");
                    editHotel.setText("");
                    editTanggal.setText("");

                    displayToast("Berhasil menambah data");
                }
            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter dbAdapter = new DBAdapter(getActivity(), null, null, 1);
                String nama = editNama.getText().toString();

                if (nama.isEmpty()) {
                    displayToast("Maaf nama harus di isi");
                } else {
                    dbAdapter.onOpen();
                    Hotel data = dbAdapter.getData(nama);
                    if (data != null) {
                        editID.setText(String.valueOf(data.get_id()));
                        editNama.setText(data.get_nama());
                        editAlamat.setText(data.get_alamat());
                        editHP.setText(data.get_hp());
                        editHotel.setText(data.get_hotel());
                        editTanggal.setText(data.get_tanggal());
                    } else {
                        editID.setText("Tidak ditemukan");
                        editNama.setText("");
                        editAlamat.setText("");
                        editHP.setText("");
                        editHotel.setText("");
                        editTanggal.setText("");
                    }
                    dbAdapter.close();
                }
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter dbAdapter = new DBAdapter(getActivity(), null, null, 1);
                dbAdapter.onOpen();
                String data = "Semua Data : \n";
                Cursor cursor = dbAdapter.getAllData();
                cursor.moveToFirst();
                do {
                    data += cursor.getString(0) + " - "
                            + cursor.getString(1) + " - "
                            + cursor.getString(2) + " - "
                            + cursor.getString(3) + " - "
                            + cursor.getString(4) + " - "
                            + cursor.getString(5) + " \n";
                }
                while (cursor.moveToNext());
                displayToast(data);
                dbAdapter.close();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter dbAdapter = new DBAdapter(getActivity(), null, null, 1);
                String nama = editNama.getText().toString();
                String alamat = editAlamat.getText().toString();
                String hp = editHP.getText().toString();
                String hotel = editHotel.getText().toString();
                String tanggal = editTanggal.getText().toString();

                if (nama.isEmpty()) {
                    displayToast("Maaf silahkan lakukan pencarian nama dahulu sebelum mengubah data");
                } else {
                    int id = Integer.parseInt(editID.getText().toString());
                    dbAdapter.onOpen();
                    int jumlah = dbAdapter.updateData(id, nama, alamat, hp, hotel, tanggal);
                    dbAdapter.close();
                    editNama.setText("");
                    editAlamat.setText("");
                    editHP.setText("");
                    editHotel.setText("");
                    editTanggal.setText("");
                    if (jumlah > 0) {
                        displayToast("Data berhasil di update");
                    } else {
                        displayToast("Data gagal di update");
                    }

                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBAdapter dbAdapter = new DBAdapter(getActivity(), null, null, 1);
                String nama = editNama.getText().toString();

                if (nama.isEmpty()) {
                    displayToast("Maaf silahkan lakukan pencarian nama dahulu sebelum mengubah data");
                } else {
                    int id = Integer.parseInt(editID.getText().toString());
                    dbAdapter.onOpen();
                    int jumlah = dbAdapter.deleteData(id);
                    dbAdapter.close();
                    editID.setText("");
                    editNama.setText("");
                    editAlamat.setText("");
                    editHP.setText("");
                    editHotel.setText("");
                    editTanggal.setText("");
                    if (jumlah > 0) {
                        displayToast("Data berhasil di hapus");
                    } else {
                        displayToast("Data gagal di hapus");
                    }

                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editID.setText("");
                editNama.setText("");
                editAlamat.setText("");
                editHP.setText("");
                editHotel.setText("");
                editTanggal.setText("");
            }
        });
    }


    public void displayToast(String toast) {
        Toast.makeText(getActivity(), toast, Toast.LENGTH_SHORT).show();
    }
}