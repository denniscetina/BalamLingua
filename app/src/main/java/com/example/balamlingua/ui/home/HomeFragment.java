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
import com.example.balamlingua.activityLeccion;
import com.example.balamlingua.databinding.FragmentHomeBinding;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private CardView saludos, numeros, tiempos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        saludos = (CardView) binding.btnSaludos;
        numeros = (CardView) binding.btnNumeros;
        tiempos = (CardView) binding.btnTiempos;
        saludos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(getActivity()).setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setCancelable(true).setGravity(Gravity.CENTER).
                        setBackgroundColorResId(R.drawable.rounded).create();
                View view1 = dialogPlus.getHolderView();
                Button btnSubir = view1.findViewById(R.id.btnApuntes);
                Button btnEmpezar = view1.findViewById(R.id.btnEmpezar);
                dialogPlus.show();
                btnSubir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), activityApuntesSaludos.class);
                        intent.putExtra("category", 1);
                        intent.putExtra("", 1);
                        startActivity(intent);
                    }
                });
                btnEmpezar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), activityLeccion.class);
                        intent.putExtra("category", 1);
                        startActivity(intent);
                        dialogPlus.dismiss();
                    }
                });

            }
        });
         tiempos.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 final DialogPlus dialogPlus = DialogPlus.newDialog(getActivity()).setContentHolder(new ViewHolder(R.layout.update_popup))
                         .setCancelable(true).setGravity(Gravity.CENTER).
                         setBackgroundColorResId(R.drawable.rounded).create();
                 View view1 = dialogPlus.getHolderView();
                 Button btnSubir = view1.findViewById(R.id.btnApuntes);
                 Button btnEmpezar = view1.findViewById(R.id.btnEmpezar);
                 dialogPlus.show();
                 btnSubir.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent = new Intent(getActivity(), activityApuntesSaludos.class);
                         startActivity(intent);
                     }
                 });
                 btnEmpezar.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent = new Intent(getActivity(), activityLeccion.class);
                         intent.putExtra("category", 2);
                         startActivity(intent);
                         dialogPlus.dismiss();
                     }
                 });

             }
         });
        numeros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(getActivity()).setContentHolder(new ViewHolder(R.layout.update_popup))
                        .setCancelable(true).setGravity(Gravity.CENTER).
                        setBackgroundColorResId(R.drawable.rounded).create();
                View view1 = dialogPlus.getHolderView();
                Button btnSubir = view1.findViewById(R.id.btnApuntes);
                Button btnEmpezar = view1.findViewById(R.id.btnEmpezar);
                dialogPlus.show();
                btnSubir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), activityApuntesSaludos.class);
                        startActivity(intent);
                    }
                });
                btnEmpezar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), activityLeccion.class);
                        intent.putExtra("category", 3);
                        startActivity(intent);
                        dialogPlus.dismiss();
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