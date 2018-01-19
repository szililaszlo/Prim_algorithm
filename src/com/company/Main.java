package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        //gráfot tároló mátrix
        int weightArray[][] = new int[20][20];
        //látogatottságot tároló tömb
        int visited[] = new int[20];
        //minimális súlyokat tároló tömb
        int d[] = new int[20];
        //csúcsokat tárok, melyeket a jelenleg vizsgált elem el tud érni
        int p[] = new int[20];
        int verticeCount,edgeCount;
        int nodeA, nodeB, weight;
        int current, total, mincost;

        // Gráf felépítés kezdete
        //bekérem a gráf éleit, csúcsait
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("\n A gráf csúcsainak száma: ");
        verticeCount = Integer.parseInt(buf.readLine());
        System.out.print("\n A gráf éleinek száma: ");
        edgeCount = Integer.parseInt(buf.readLine());
        //minden él súlyát beállítom nullára
        //ahol nulla a súly ott nincs él
        for (int i = 1; i <= verticeCount; i++) {
            for (int j = 1; j <= verticeCount; j++) {
                weightArray[i][j] = 0;
            }
        }

        //beállítok minden élt nem látogatottra
        //az integer típus maximumát használom súlynak és beállítom az összes élre
        for (int i = 1; i <= verticeCount; i++) {
            p[i] = visited[i] = 0;
            d[i] = 32767;
        }

        //Bekérek 2 csúcsot és köztük lévő él súlyát, mindezt annyiszor ahány él van
        //ezeket el is tárolom a mátrixban
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 1; i <= edgeCount; i++) {
            System.out.print("\n Kérek két csúcsot és a köztük lévő él súlyát: "); //nodeA, nodeB and weightArray weight
            nodeA = Integer.parseInt(in.readLine());
            nodeB = Integer.parseInt(in.readLine());
            weight = Integer.parseInt(in.readLine());
            weightArray[nodeA][nodeB] = weightArray[nodeB][nodeA] = weight;
        }
        // Gárf felépítés vége


        //1-es csúcsból indulok
        current = 1;
        //1-ből 1-be nincs költség
        d[current] = 0;
        //kezdetben az új gráfba 1 csúcs van
        total = 1;
        //az egyes csúcs már látogatott
        visited[current] = 1;

        //ha nem minden csúcs lett bevéve az új gráfba
        while (total != verticeCount) {
            //csúcsbejárás
            for (int i = 1; i <= verticeCount; i++) {
                //ha van köztük él,azaz számításba veszi azokat a csúcsokat amleyk elérhetők a jelenlegiből
                if (weightArray[current][i] != 0) {
                    //ha még nem látogatott
                    if (visited[i] == 0) {
                        //ha egy súly a mátrixban kisebb mint a súlyokat tároló tömb i-edik súlya
                        if (d[i] > weightArray[current][i]) {
                            //a d tömb i-edik eleme egyenlő lesz téve az álltalunk megadot él súllyal
                            d[i] = weightArray[current][i];
                            //eltárolom azt, hogy a p tömbben tárolt csúcs kapcsolódhat a jelenlegi ecsúcshoz
                            p[i] = current;
                        }
                    }
                }
            }

            // itt fog megtörténni az új gráfhoz még nem kapcsolódó csúcs hozzáadása, a lehetséges opciók közül a
            // legkisebbet választva
            mincost = 32767;
            for (int i = 1; i <= verticeCount; i++) {
                //ha egy csúcs nem látogatott
                if (visited[i] == 0) {
                    // ha a még nem látogatott csúcshoz tartozó súly kisebb mint a jelenlegi súly
                    if (d[i] < mincost) {
                        //ha megfelelt  az ő súlya lesz az új legkisebb súly
                        mincost = d[i];
                        //ő lesz az aktuális elem
                        current = i;
                    }
                }
            }

            //a ciklusban a legkisebb költséggel elérhető,még nem látogatott csúcs -> látogatott lett
            visited[current] = 1;
            //új csúcs került a gráfba
            total++;
            // a while ciklus újra kezdődik, és az aktuális elem az ímént látogatott elem lett
        }

        //minimális súly kiszámítás
        mincost = 0;
        for (int i = 1; i <= verticeCount; i++)
            mincost = mincost + d[i];

        //kiíratás

        System.out.print("\n Minimum költség= " + mincost);
        System.out.print("\n Minimális súlyú feszítőfa:");

        for (int i = 1; i <= verticeCount; i++)
            System.out.print("\n A(z) " + i + " csúcs kapcsolódik a(z) " + p[i] + " csúcshoz");
    }


}
