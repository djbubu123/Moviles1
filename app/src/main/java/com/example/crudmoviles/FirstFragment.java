package com.example.crudmoviles;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.crudmoviles.databinding.FragmentFirstBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class FirstFragment extends Fragment implements View.OnClickListener{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FragmentFirstBinding binding;
   EditText inputNombre=(EditText) getView().findViewById(R.id.inputNombre);
   EditText inputPrecio=(EditText) getView().findViewById(R.id.inputPrecio);
   EditText inputEstado=(EditText) getView().findViewById(R.id.inputEstado);
   EditText inputDescripcion=(EditText) getView().findViewById(R.id.inputDescripcion);


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        Button crearButton=(Button) getView().findViewById(R.id.button);
        crearButton.setOnClickListener(this);
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

       // binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View view) {
       //         NavHostFragment.findNavController(FirstFragment.this)
       //                 .navigate(R.id.action_FirstFragment_to_SecondFragment);
       //     }
       // });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View view) {
        String nombre = inputNombre.getText().toString();
        String precio = inputPrecio.getText().toString();
        String estado = inputEstado.getText().toString();
        String descripcion = inputDescripcion.getText().toString();
        Map<String, Object> producto = new HashMap<>();
        producto.put("nombre", nombre);
        producto.put("precio", precio);
        producto.put("estado", estado);
        producto.put("descripcion", descripcion);
        db.collection("producto")
                .add(producto)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}