package wordle;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
class Node implements Comparable<Node>
{
    public char c;
    public int f;
    public Node leftChild;
    public Node rightChild;

    public Node(char c, int f)
    {
        this.c = c;
        this.f = f;
    }

    public Node(Node leftChild, Node rightChild)
    {
        this.f = leftChild.f + rightChild.f;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public int compareTo(Node node) {
        return this.f - node.f;
    }
}


public class Huffman
{

    public static int[] frequencyChecker(String fileName)
    {
        int[] letterFrequency = new int[26];
        try(BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while((line = reader.readLine()) != null)
            {
                for(char letter : line.toCharArray())
                {
                    letterFrequency[letter - 'a'] ++;
                }
            }
        }catch (IOException e)
        {
            System.err.println("Error");
        }

        return letterFrequency;
    }

    public static void main(String[] args) {
        int[] letterFrequency = new int[26];
        String fileName = "/Users/leo/Desktop/Software_Engineering_Project1/datastructures20280-23/src/wordle/resources/dictionary.txt";

        int[] Frequency = frequencyChecker(fileName);

        for (int i = 0; i < Frequency.length; i++)
        {
            System.out.println((char) (i + 'a') + ": " + Frequency[i]);
        }
    }
}


