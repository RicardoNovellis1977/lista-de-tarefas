package com.example.ricardo.listadetarefas.activity;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.ricardo.listadetarefas.R;
import com.example.ricardo.listadetarefas.helper.TarefaDAO;
import com.example.ricardo.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText txtTarefa;
    private Tarefa tarefaatual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        txtTarefa = findViewById(R.id.txtTarefaAdicionar);
        tarefaatual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        if (tarefaatual != null) {
            txtTarefa.setText(tarefaatual.getNomeTarefa());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.itemSalvar:

                TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());

                if (tarefaatual != null) {

                    String nomeTarefa = txtTarefa.getText().toString();

                    if (!nomeTarefa.isEmpty()) {

                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);
                        tarefa.setId(tarefaatual.getId());

                        if (tarefaDAO.atualizar(tarefa)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao atualizar a tarefa !", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao tualizar a tarefa !", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    String nomeTarefa = txtTarefa.getText().toString();
                    if (!nomeTarefa.isEmpty()) {
                        Tarefa tarefa = new Tarefa();
                        tarefa.setNomeTarefa(nomeTarefa);

                        if (tarefaDAO.salvar(tarefa)) {
                            finish();
                            Toast.makeText(getApplicationContext(), "Sucesso ao salvar a tarefa !", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar a tarefa !", Toast.LENGTH_LONG).show();
                        }
                    }
                    break;
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
