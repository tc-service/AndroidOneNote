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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

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

        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextInputEditText textInputEditText = view.findViewById(R.id.etIn);
        TextView textView = view.findViewById(R.id.tvTitle);
        // Получить из ресурсов массив указателей на изображения гербов
        TypedArray contents = getResources().obtainTypedArray(R.array.content);
        TypedArray titles = getResources().obtainTypedArray(R.array.titles);

        // Выбрать по индексу подходящий
        textInputEditText.setText(contents.getText(index));
        textView.setText(titles.getText(index));

    }
}
