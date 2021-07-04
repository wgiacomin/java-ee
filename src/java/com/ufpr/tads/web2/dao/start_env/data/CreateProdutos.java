package com.ufpr.tads.web2.dao.start_env.data;

import com.ufpr.tads.web2.dao.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;

public class CreateProdutos {

    private static Statement query = null;
    private static Connection con = null;

    public static void main(String[] args) {
        try (ConnectionFactory factory = new ConnectionFactory()) {
            con = factory.getConnection();
            query = con.createStatement();

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Esponja de Maquiagem', "
                    + "'A Acqua Blend é perfeita para aplicar base, corretivo e pó compacto. Expande de tamanho quando molhada, proporcionando um acabamento natural e uniforme.', "
                    + "0.3, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Maquiagem';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Máscara de Cílios the Colossal Lavável', "
                    + "'The Colossal Volum Express Máscara é uma máscara para cílios de volume extremo.', "
                    + "20, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Maquiagem';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'BB Cream Dermo Expertise Base Clara 30ml', "
                    + "'Loreal Paris BB Cream 5 em 1 FPS 20 é livre de óleos e com protetor solar físico (dióxido de titânio) que evita alergias, foi especialmente desenvolvida para todos os tipos de pele, inclusive para pele oleosa.', "
                    + "49, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Maquiagem';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Eau de Toilette Sexy Woman', "
                    + "'Perfume feminino floral. Como o próprio nome revela, é uma fragrância com poder de sedução. O perfume sexy woman tem acordes de madeiras, como o sândalo, além de especiarias. Combinados com flores, fazem jus ao nome feminino e sexy.', "
                    + "302, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Perfume';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Calvin Klein Eternity Feminino Eau De Parfum 100Ml', "
                    + "'O perfume Calvin Klein eternity for women contém uma elegância atemporal. Eternity for women é um perfume feminino simples e, ao mesmo tempo, sofisticado. Floral, mas picante. Inspirado pelos romances modernos, eternity for women tem notas de topo florais de lírio branco e bergamota. Depois, faz uma transição para essências com pétalas de rosas brancas e violetas, finalizando com notas secas e picantes de sândalo e âmbar dourado. Uma fragrância feminina clássica para mulheres contemporâneas.', "
                    + "20.98, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Perfume';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Delikad Butterfly Collection Deo Colônia Dream', "
                    + "'Imagine-se flutuando nas asas de uma borboleta, sentindo o vento bater em seu rosto, alcançando altitudes inimagináveis, usufruindo o momento e aguçando seus sentidos para essas novas sensações. Venha viajar nas asas da nova fragrâncias de Delikad: Dream! Aproveite este voo para experimentar o que nunca foi vivido antes!', "
                    + "400, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Perfume';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Máscara Super Hidratante Morte Súbita', "
                    + "'A máscara de nutrição da Lola Cosmetics é um creme de hidratação de tratamento semanal para cabelos ressecados e/ou danificados. Restaura os fios e proporciona suavidade, força e desembaraçar sublime. O creme Morte Súbita é perfeito para dia a dia e para o pós-processo de coloração. Possui um aroma delicioso e vem em embalagens de 450 gramas.', "
                    + "480, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Cabelo';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Inoar Kit shampoo e condicionador CicatriFios Plástica Capilar 1L', "
                    + "'O Inoar Kit Shampoo e Condicionador tem fórmula com rejucomplex3 e óleo de argan, facilita a escovação e age para a redução de quebra, proporcionando frizz e brilho extremo. O shampoo e condicionador Inoar são produto veganos, liberado para as técnicas low poo, no poo e co-wash.', "
                    + "2230, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Cabelo';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Inoar Kit Shampoo e Condicionador #Bombar Crescimento Capilar 1L', "
                    + "'A linha inoar bombar foi criada para reunir ativos poderosos e restaurar a saúde e maciez dos cabelos. O shampoo contém d-pantenol, ingrediente que promove hidratação e brilho de longa duração aos fios, e biotina. Conhecida como a vitamina da beleza, ela ajuda no crescimento e fortalecimento dos fios. O condicionador foi formulado com biotina e ingredientes que promovem maciez imediata.', "
                    + "2000, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Cabelo';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Sérum Facial Vitamina C 10', "
                    + "'Esse sérum facial com vitamina C10 da Tracta tem efeito clareador e deixa a pele luminosa e uniforme. Também possui ação antioxidante, ação firmadora, ação antirrugas e antienvelhecimento. Em apenas um produto, você consegue todos esses benefícios para a sua pele. Ele pode ser usado na cútis antes de aplicar a base e o corretivo da Tracta. Indicado para todos os tipos de pele.', "
                    + "88.5, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Rosto';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Creme Hidratante Toque Seco', "
                    + "'O Creme Hidratante Bepantol Derma possui toque seco e hidratação intensa. Além da alta concentração de dexpantenol, este creme tem em sua fórmula lanolina e óleo de amêndoas doces que aumentam ainda mais seu poder hidratante.', "
                    + "47, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Rosto';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Creme Revitalift Hialurônico Noturno', "
                    + "'O novo Revitalift Hialurônico é um creme facial com ação antirrugas. Com textura leve, ele preenche as linhas de expressão por causa do ácido hialurônico e funciona como um creme para o rosto, deixando a sensação da pele mais macia e hidratada.', "
                    + "245, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Rosto';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Creme Hydro Boost Water Gel', "
                    + "' O hidratante facial da Neutrogena tem muitos benéficos, tem textura leve e suave e duração de 48 horas. Esse creme hidratante possui ácido hialurônico, que combate sinais de envelhecimento, além de propriedades de controle da oleosidade. Ele vem em uma embalagem de 50g. O hidratante Netrogena para o rosto tem textura leve e é ideal para todos os tipos de pele.', "
                    + "250, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Corpo';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Sabonete Líquido Purified Skin', "
                    + "'O gel de limpeza neutrogena purified skin oferece uma limpeza purificante e profunda que elimina as impurezas e toxinas e reduz a oleosidade da pele sem deixar a sensação de ressecamento.', "
                    + "910, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Corpo';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Gel Hidratante Hydro Boost Body Ntg', "
                    + "'O Neutrogena Hydro Boost promove hidratação intensa, sensação de leveza e melhora a aparência da pele.', "
                    + "500, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Corpo';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Lixa Polidora Bloco 7 Faces Steps Para Unhas Gel Acrigel', "
                    + "'Lixa de alta qualidade para uso profissional ou doméstico. Possui 7 faces, cada uma para uma etapa do tratamento das unhas.', "
                    + "100, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Manicure e Pedicure';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Esmalte Alfazema', "
                    + "'O esmalte marshmallow de alfazema possui acabamento lilás azulado cremoso e faz parte da linha de esmaltes coloridos da risqué. A moda não é apenas o que você usa, é também o que você sente. Por isso, risqué tem diversas opções entre os tons naturais, naturais com efeito gloss, cremosos, ultra-cremosos, metálicos e cintilantes. Além disso, os esmaltes risqué possuem três benefícios em um só produto. Sua formulação, aprovada por consumidoras de todo brasil, garante longa duração, secagem rápida e ultra brilho, cremosos, cintilantes e metálicos. Um para cada sensação, para cada momento.', "
                    + "33, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Manicure e Pedicure';");

            query.executeUpdate("INSERT INTO produto(nome, descricao, peso, fk_categoria) "
                    + "SELECT 'Sabonete Líquido Para As Mãos Protex Balance 1L', "
                    + "'O Sabonete Líquido para Mãos da Protex oferece um perfeito equilíbrio entre proteção antibacteriana e ingredientes umectantes e suavizantes, especialmente desenvolvido para toda a família. Instruções de uso: Aplique uma pequena quantidade nas mãos esfregando-as até criar um pequena espuma. Enxague logo em seguida. Precauções: Manter fora do alcance de crianças. Evite contato com os olhos. Havendo irritação ou sensibilidade, suspenda o uso e consulte um dermatologista.', "
                    + "1100, "
                    + "id "
                    + "FROM produto_categoria WHERE descricao = 'Manicure e Pedicure';");

            System.out.println("Produtos criados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao criar ao criar Produtos.");
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                    con = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (query != null) {
                try {
                    query.close();
                    query = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
