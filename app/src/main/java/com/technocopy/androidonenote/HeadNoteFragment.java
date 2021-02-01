package com.technocopy.androidonenote;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HeadNoteFragment extends Fragment {

    public static final String CURRENT_NOTE = "CurrentNote";
    private int currentPosition = 0;    // Текущая позиция заметки

    private  boolean isLandscape;

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_head_note, container, false);
    }

    // вызывается после создания макета фрагмента, инициализируем список
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
//                        showPortNoteContent(fi);
                        currentPosition = fi;
                        showNoteContent(currentPosition);
                    }
                });
            }
        }
    }
    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_NOTE, currentPosition);
        super.onSaveInstanceState(outState);
    }

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //определяем положение экрана
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        // Если это не первое создание, то восстановим текущую позицию
        if (savedInstanceState != null) {
            // Восстановление текущей позиции.
            currentPosition = savedInstanceState.getInt(CURRENT_NOTE, 0);
        }

        if (isLandscape) {
            showLandNoteContent(0);
        }
    }

    private void showNoteContent(int index) {
        if (isLandscape) {
            showLandNoteContent(index);
        } else {
            showPortNoteContent(index);
        }
    }

    // Показать заметку в ландшафтной ориентации
    private void showLandNoteContent(int index) {
        // Создаём новый фрагмент с текущей позицией для показа заметки
        NoteFragment detail = NoteFragment.newInstance(index);

        // Выполняем транзакцию по замене фрагмента
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.etIn, detail);  // замена фрагмента
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }

    private void showPortNoteContent(int index) {
        // Откроем вторую activity
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent();
            intent.setClass(context, NoteContentActivity.class);
            //  передаем туда параметры
            intent.putExtra(NoteFragment.ARG_INDEX, index);
            startActivity(intent);
        }

    }
}
