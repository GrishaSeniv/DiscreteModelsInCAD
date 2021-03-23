package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.io.*;

public class BoruvkaUnitTest {

    private MutableValueGraph<Integer, Integer> graph;

    @Before
    public void setup() {
        graph = ValueGraphBuilder.undirected()
                .build();
        int[][] array = new int[8][8];
        String[] words;
        int lineCount = 0;

        try {
            File file = new File("C:\\Users\\Grisha\\Desktop\\Ну ЛП\\4 курс\\2 семестр\\Дискретна математика\\Лабораторна робота №1\\ParthFromTxt\\l1_2.txt");
            //Create Object FileReader for Object File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            String line = reader.readLine();
            line = reader.readLine(); //

            while (line != null) {
                words = line.split(" ");

                for (int i = 0; i < 8; i++) {
                    array[lineCount][i] = Integer.parseInt(words[i]);
                    //System.out.print(array[lineCount][i] + " ");
                }
                //System.out.println();

                lineCount++;

                // считываем остальные строки в цикле
                line = reader.readLine();
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        int lineRound = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = lineRound; j < 8; j++) {
                //System.out.print(array[i][j] + " ");
                if (array[i][j] != 0) {
                    System.out.println("i = " + i);
                    System.out.println("j = " + j);
                    System.out.println("el = " + array[i][j]);
                    graph.putEdgeValue(i, j, array[i][j]);
                }
            }
            System.out.println();
            lineRound++;
        }
    }

    @Test
    public void givenInputGraph_whenBoruvkaPerformed_thenMinimumSpanningTree() {
        BoruvkaMST boruvkaMST = new BoruvkaMST(graph);
        MutableValueGraph<Integer, Integer> mst = boruvkaMST.getMST();

        assertEquals(112, boruvkaMST.getTotalWeight());
        System.out.println("Total weight = " + boruvkaMST.getTotalWeight());
        assertEquals(7, mst.edges().size());
    }

}

