package br.com.fabricaapp.chamadapersonalizada.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.fabricaapp.chamadapersonalizada.R;
import br.com.fabricaapp.chamadapersonalizada.adapters.AlunoAdapter;
import br.com.fabricaapp.chamadapersonalizada.controllers.AlunoBancoController;
import br.com.fabricaapp.chamadapersonalizada.entities.Aluno;

public class MainActivity extends AppCompatActivity {
    ListView lvAlunos;
    ArrayList<Aluno> listaDeAlunos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvAlunos = (ListView)findViewById(R.id.lvAlunos);
        buildListaAluno();
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizarLista();
    }

    public void buildListaAluno(){


        listaDeAlunos = new ArrayList<>();
        listaDeAlunos.add(new Aluno(0,"Didi",""));
        listaDeAlunos.add(new Aluno(0,"Dedê",""));
        listaDeAlunos.add(new Aluno(0,"Mussum",""));
        listaDeAlunos.add(new Aluno(0,"Zecarias",""));

        AlunoAdapter adapter = new AlunoAdapter(this,listaDeAlunos);

        lvAlunos.setAdapter(adapter);

    }

    public void atualizarLista(){
        Cursor cursor = new AlunoBancoController(this).getAll();
        listaDeAlunos.clear();
        while (cursor.moveToNext()){
            Aluno aluno = new Aluno(
                    cursor.getInt(cursor.getColumnIndex("id")),
                    cursor.getString(cursor.getColumnIndex("nome")),
                    cursor.getString(cursor.getColumnIndex("foto")));
            listaDeAlunos.add(aluno);
        }
        lvAlunos.deferNotifyDataSetChanged();
    }

    public void abrirAdd(View view){
        Intent it = new Intent(this,AddActivity.class);
        startActivity(it);
    }
}
