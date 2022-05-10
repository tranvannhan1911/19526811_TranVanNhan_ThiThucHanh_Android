package com.example.a19526811_tranvannhan_thithuchanh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a19526811_tranvannhan_thithuchanh.adapter.BookRecyclerAdapter;
import com.example.a19526811_tranvannhan_thithuchanh.entity.Book;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("books");

        RecyclerView recycler = findViewById(R.id.recycler);
        BookRecyclerAdapter bookRecyclerAdapter
                = new BookRecyclerAdapter(this);
        recycler.setAdapter(bookRecyclerAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        Button btnAdd = findViewById(R.id.btn_add);
        EditText edtBook = findViewById(R.id.edt_book);
        btnAdd.setOnClickListener(v -> {
            String bookName = edtBook.getText().toString();
            myRef.push().setValue(bookName).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(HomeActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Log.d("firebase", "success");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(HomeActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    Log.d("firebase", "fail");
                }
            }).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("firebase", "complete");
                }
            });
        });

        Button btnEdit = findViewById(R.id.btn_edit);
        btnEdit.setOnClickListener(v -> {
            String bookName = edtBook.getText().toString();
            String key = bookRecyclerAdapter.getKey();
            myRef.child(key).setValue(bookName).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(HomeActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Log.d("firebase", "success");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(HomeActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    Log.d("firebase", "fail");
                }
            }).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Log.d("firebase", "complete");
                }
            });
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Book> lst = new ArrayList<>();
                for(DataSnapshot child : snapshot.getChildren()){
                    Log.d("key", child.getKey());

                    lst.add(new Book(child.getKey(), child.getValue(String.class)));
                }
                bookRecyclerAdapter.setLst(lst);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("firebase", "cancel");
            }
        });
    }

    public void delete(String key){
        myRef.child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(HomeActivity.this, "Success", Toast.LENGTH_SHORT).show();
                Log.d("firebase", "success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(HomeActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                Log.d("firebase", "fail");
            }
        }).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Log.d("firebase", "complete");
            }
        });
    }
}