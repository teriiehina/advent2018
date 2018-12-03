package pf.teriiehina.advent2018.day01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day01
{
    public static void main(String[] args)
    {
        String filePath = args.length >= 2 ? args[1] : "datas/day01.txt";
        Day01  day01    = new Day01();

        List<Integer> datas    = day01.loadDatasFromFile(filePath);
        Integer       sum      = datas.stream().reduce(0, Integer::sum);
        Integer       firstDup = day01.findFirstFrequencyDup(datas);

        System.out.println("Sum of frequencies: " + sum);
        System.out.println("First frequency dup: " + firstDup);
    }

    private Integer findFirstFrequencyDup(List<Integer> datas)
    {
        Map<Integer, Integer> sums      = new HashMap<>();
        Integer               frequency = 0;

        while (true)
        {
            for (Integer change : datas)
            {
                frequency = frequency + change;

                if (sums.containsKey(frequency))
                {
                    return frequency;
                }
                else
                {
                    sums.put(frequency, 1);
                }
            }
        }
    }

    private List<Integer> loadDatasFromFile(String csvPath)
    {
        String        file    = csvPath;
        List<Integer> content = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line = "";
            while ((line = br.readLine()) != null)
            {
                content.add(Integer.valueOf(line));
            }
        } catch (IOException e)
        {
            System.out.println(e);
        }
        return content;
    }
}
