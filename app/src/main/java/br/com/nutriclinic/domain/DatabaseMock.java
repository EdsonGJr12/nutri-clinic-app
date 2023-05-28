package br.com.nutriclinic.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseMock {

    private static List<DiaRefeicoesDTO> data = new ArrayList<>();

    static {
        // Segunda feira
        RefeicaoDTO refeicao1 = new RefeicaoDTO("Café da manhã", "07:00 AM");
        RefeicaoDTO refeicao2 = new RefeicaoDTO("Lanche", "10:00 AM");
        RefeicaoDTO refeicao3 = new RefeicaoDTO("Almoço", "13:00 AM");
        RefeicaoDTO refeicao4 = new RefeicaoDTO("Lanche da tarde", "16:00 AM");

        data.add(new DiaRefeicoesDTO("Segunda", List.of(refeicao1, refeicao2, refeicao3, refeicao4)));


        // Terça feira
        RefeicaoDTO refeicao5 = new RefeicaoDTO("Café da manhã", "08:00 AM");
        RefeicaoDTO refeicao6 = new RefeicaoDTO("Lanche", "10:00 AM");
        RefeicaoDTO refeicao7 = new RefeicaoDTO("Almoço", "13:00 AM");
        RefeicaoDTO refeicao8 = new RefeicaoDTO("Lanche da tarde", "16:00 AM");

        data.add(new DiaRefeicoesDTO("Terça", List.of(refeicao5, refeicao6, refeicao7, refeicao8)));

        // Quarta feira
        RefeicaoDTO refeicao9 = new RefeicaoDTO("Café da manhã", "09:00 AM");
        RefeicaoDTO refeicao10 = new RefeicaoDTO("Lanche", "10:00 AM");
        RefeicaoDTO refeicao11 = new RefeicaoDTO("Almoço", "13:00 AM");
        RefeicaoDTO refeicao12 = new RefeicaoDTO("Lanche da tarde", "16:00 AM");

        data.add(new DiaRefeicoesDTO("Quarta", List.of(refeicao9, refeicao10, refeicao11, refeicao12)));

        // Quinta feira
        RefeicaoDTO refeicao13 = new RefeicaoDTO("Café da manhã", "07:00 AM");
        RefeicaoDTO refeicao14 = new RefeicaoDTO("Lanche", "11:00 AM");
        RefeicaoDTO refeicao15 = new RefeicaoDTO("Almoço", "13:00 AM");
        RefeicaoDTO refeicao16 = new RefeicaoDTO("Lanche da tarde", "16:00 AM");

        data.add(new DiaRefeicoesDTO("Quinta", List.of(refeicao13, refeicao14, refeicao15, refeicao16)));

        // Sexta feira
        RefeicaoDTO refeicao17 = new RefeicaoDTO("Café da manhã", "07:00 AM");
        RefeicaoDTO refeicao18 = new RefeicaoDTO("Lanche", "12:00 AM");
        RefeicaoDTO refeicao19 = new RefeicaoDTO("Almoço", "13:00 AM");
        RefeicaoDTO refeicao20 = new RefeicaoDTO("Lanche da tarde", "16:00 AM");

        data.add(new DiaRefeicoesDTO("Sexta", List.of(refeicao17, refeicao18, refeicao19, refeicao20)));

        // Sábado
        RefeicaoDTO refeicao21 = new RefeicaoDTO("Café da manhã", "07:00 AM");
        RefeicaoDTO refeicao22 = new RefeicaoDTO("Lanche", "10:00 AM");
        RefeicaoDTO refeicao23 = new RefeicaoDTO("Almoço", "14:00 AM");
        RefeicaoDTO refeicao24 = new RefeicaoDTO("Lanche da tarde", "16:00 AM");

        data.add(new DiaRefeicoesDTO("Sábado", List.of(refeicao21, refeicao22, refeicao23, refeicao24)));

        // Domingo
        RefeicaoDTO refeicao25 = new RefeicaoDTO("Café da manhã", "07:00 AM");
        RefeicaoDTO refeicao26 = new RefeicaoDTO("Lanche", "10:00 AM");
        RefeicaoDTO refeicao27 = new RefeicaoDTO("Almoço", "14:00 AM");
        RefeicaoDTO refeicao28 = new RefeicaoDTO("Lanche da tarde", "16:00 AM");

        data.add(new DiaRefeicoesDTO("Domingo", List.of(refeicao25, refeicao26, refeicao27, refeicao28)));
    }

    public static List<DiaRefeicoesDTO>  getData() {
        return data;
    }
}
