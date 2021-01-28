package com.technocopy.androidonenote;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class NoteFragment extends Fragment {



    static final String ARG_INDEX = "index";
    private int index;


    public static NoteFragment newInstance(int index) {
        NoteFragment f = new NoteFragment();    // создание

        // Передача параметра
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Таким способом можно получить головной элемент из макета
 //       View view = inflater.inflate(R.layout.fragment_note, container, false);
        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // найти в контейнере элемент
        EditText editText = view.findViewById(R.id.tvIn);

        // Получить из ресурсов массив content
        TypedArray contents = getResources().obtainTypedArray(R.array.content);
        // Выбрать по индексу подходящий
        editText.setText(contents.getResourceId(index, -1));

    }
}
