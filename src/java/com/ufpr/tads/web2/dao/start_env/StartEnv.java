package com.ufpr.tads.web2.dao.start_env;

import com.ufpr.tads.web2.dao.start_env.data.CreateAtendimento;
import com.ufpr.tads.web2.dao.start_env.data.CreateCadastro;
import com.ufpr.tads.web2.dao.start_env.data.CreateCategorias;
import com.ufpr.tads.web2.dao.start_env.data.CreateUF;
import com.ufpr.tads.web2.dao.start_env.data.CreateCidades_1;
import com.ufpr.tads.web2.dao.start_env.data.CreateCidades_2;
import com.ufpr.tads.web2.dao.start_env.data.CreateLogins;
import com.ufpr.tads.web2.dao.start_env.data.CreatePerfil;
import com.ufpr.tads.web2.dao.start_env.data.CreateProdutos;
import com.ufpr.tads.web2.dao.start_env.data.CreateStatus;
import com.ufpr.tads.web2.dao.start_env.data.CreateTables;
import com.ufpr.tads.web2.dao.start_env.data.CreateTipoAtendimento;
import com.ufpr.tads.web2.dao.start_env.data.CreateViews;

public class StartEnv {
    public static void main(String[] args) {
        CreateTables.main(args);
        CreateViews.main(args);
        CreateLogins.main(args);
        CreateUF.main(args);
        CreateCidades_1.main(args);
        CreateCidades_2.main(args);
        
        CreateCategorias.main(args);
        CreateProdutos.main(args);
        CreatePerfil.main(args);
        CreateTipoAtendimento.main(args);
        CreateStatus.main(args);
        CreateCadastro.main(args);
        CreateAtendimento.main(args);
    }
}
