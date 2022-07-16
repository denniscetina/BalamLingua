package com.example.balamlingua.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.balamlingua.R;
import com.example.balamlingua.databinding.FragmentHomeBinding;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private CardView saludos,numeros,tiempos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        saludos = (CardView)binding.btnSaludos;
        saludos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(getActivity()).setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setCancelable(true).setGravity(Gravity.CENTER).
                                setBackgroundColorResId(R.drawable.rounded).create();
                //dialogPlus.show();
                View view1 =dialogPlus.getHolderView();
                //EditText nombre = view1.findViewById(R.id.txtNombre);
               // EditText cantidad = view1.findViewById(R.id.txtCantidad);
               // EditText precio = view1.findViewById(R.id.txtPrecio);
               Button btnSubir = view1.findViewById(R.id.btnApuntes);

                //nombre.setText(model.getNombre());
               // cantidad.setText(model.getCantidad());
               // precio.setText(model.getPrecio());
                dialogPlus.show();
                btnSubir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), activityApuntesSaludos.class);
                        startActivity(intent);
                    }
                });

            }
        });


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}