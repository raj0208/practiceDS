package com.raj.practice.Misc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reactive Java by JavaBrains
 * https://www.youtube.com/watch?v=-uQcnnY1ofY&list=PLqq-6Pq4lTTYPR2oH7kgElMYZhJd4vOGI&index=8
 */
public class ReactiveJava {
    static Supplier<Stream<Integer>> getIntStream = () -> Stream.of(1,2,3,4,5, 6);
    static Supplier<Stream<StreamsRnd.Employee>> getEmployeeStream = () ->
        Stream.of(
                new StreamsRnd.Employee("AAA", 1, true ),
                new StreamsRnd.Employee("BBB", 2, true ),
                new StreamsRnd.Employee("CCC", 3, false ),
                new StreamsRnd.Employee("DDD", 4, false ),
                new StreamsRnd.Employee("EEE", 5, true )
        );

    public static void main(String[] args) {
        ladderLength();
    }

    private static void streamFilter() {
        Set<Integer> ids =
                getIntStream.get().collect(Collectors.toSet());
        getEmployeeStream.get().filter(x -> ids.contains(x.getDept())).map(x -> x.getName()).forEach(System.out::println);


        getIntStream.get()
                .flatMap(x -> getEmployeeStream.get().filter(emp -> emp.getDept() == x))
                .map(x -> x.getName())
                .forEach(System.out::println);
    }

    static class WordNode{
        String word;
        int numSteps;
        public WordNode(String word, int numSteps){
            this.word = word;
            this.numSteps = numSteps;
        } }

    public static int ladderLength() {
        String beginWord = "hit";
        String endWord = "cog";
        Set<String> wordDict =  new HashSet<>(Arrays.asList("hot","dot","dog","lot","log"));

        LinkedList<WordNode> queue = new LinkedList<WordNode>();
        queue.add(new WordNode(beginWord, 1));
        wordDict.add(endWord);
        while(!queue.isEmpty()){
            WordNode top = queue.remove();
            String word = top.word;
            if(word.equals(endWord)){
                return top.numSteps;
            }
            char[] arr = word.toCharArray();
            for(int i=0; i<arr.length; i++){
                for(char c='a'; c<='z'; c++){
                    char temp = arr[i];
                    if(arr[i]!=c){
                        arr[i]=c; }
                    String newWord = new String(arr);
                    if(wordDict.contains(newWord)){
                        queue.add(new WordNode(newWord, top.numSteps+1));
                        wordDict.remove(newWord);
                    }
                    arr[i]=temp;
                }
            } }
        return 0;
    }

}


