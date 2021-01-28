package com.technocopy.androidonenote;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Head_Note_Fragment extends Fragment {

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_head_note, container, false);
    }

    // вызывается после создания макета фрагмента, здесь мы проинициализируем список
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    // создаём список заметок на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] titles = getResources().getStringArray(R.array.titles);

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаём обработку касания на элемент
        for (int i = 0; i < titles.length; i++) {
            Context context = getContext();
            if (context != null) {
                String title = titles[i];
                TextView tv = new TextView(context);
                tv.setText(title);
                tv.setTextSize(30);
                layoutView.addView(tv);

                final int fi = i;
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPortNoteContent(fi);
                    }
                });
            }
        }
    }

    private void showPortNoteContent(int index) {
        // Откроем вторую activity
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent();
            intent.setClass(context, NoteContentActivity.class);
            // и передадим туда параметры
            intent.putExtra(NoteFragment.ARG_INDEX, index);
            startActivity(intent);
        }

    }
}
