package com.scy.java8.inaction.chapter7;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 类名： SpliteratorDemo <br>
 * 描述：TODO <br>
 * 创建日期： 2020/7/27 <br>
 *
 * @author suocaiyuan
 * @version V1.0
 */
public class SpliteratorDemo {

    final String SENTENCE = " Nel mezzo del cammin di nostra vita " +
            "mi ritrovai in una selva oscura" +
            " ché la dritta via era smarrita ";

    //判定string中包含空白字符个数
    public int countWordIteratively(String s) {
        int counter = 0;
        boolean lastSpace = true;
        for (char c : s.toCharArray()) {
            if (Character.isWhitespace(c)) {
                lastSpace = true;
            } else {
                if (lastSpace) counter++;
                lastSpace = false;
            }
        }
        return counter;
    }

    public Stream<Character> getStream(String s) {
        return IntStream.range(0, s.length())
                .mapToObj(s::charAt);
    }

    //================================================================================

    public int countWords(Stream<Character> stream) {
        WordCounter counter = stream.reduce(new WordCounter(0, true),
                WordCounter::accumulate, WordCounter::combine);
        return counter.getCounter();
    }


    public static void main(String[] args) {
        SpliteratorDemo sd = new SpliteratorDemo();
        System.out.println(sd.countWordIteratively(sd.SENTENCE));
    }
}

class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c)) {
            return lastSpace ? this : new WordCounter(counter, true);
        } else {
            return lastSpace ? new WordCounter(counter + 1, false) : this;
        }
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        SpliteratorDemo sd = new SpliteratorDemo();
        String s = sd.SENTENCE;
        Stream<Character> stream = sd.getStream(s);
        System.out.println("Found " + sd.countWords(stream) + " words");
        //并行处理 反而不理想
        System.out.println("Found " + sd.countWords(stream.parallel()) + " words");
    }
}

class WordCounterSpliterator implements Spliterator<Character> {
    private final String string;
    private int currentChar = 0;

    public WordCounterSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        //普通模式 处理字符
        action.accept(string.charAt(currentChar++));
        //如果还有字符需要处理  返回true
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        //返回null表示要解析的 String已经足够小，可以顺序处理
        if (currentSize < 10) {
            return null;
        }
        //将试探拆分位置设定为要解析的String的中间
        for (int splitPos = currentSize / 2 + currentChar; splitPos < string.length(); splitPos++) {
            //让拆分位置前进直到下一个空格
            if (Character.isWhitespace(string.charAt(splitPos))) {
                //创建一个新WordCounterSpliterator来解析String从开始到拆分位置的部分
                Spliterator<Character> spliterator =
                        new WordCounterSpliterator(string.substring(currentChar, splitPos));
                //将这个WordCounterSpliterator 的起始位置设为拆分位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

    public static void main(String[] args) {
        SpliteratorDemo sd = new SpliteratorDemo();
        String sentence = " Nel mezzo del cammin di nostra vita " +
                "mi ritrovai in una selva oscura" +
                " ché la dritta via era smarrita ";
        Spliterator<Character> spliterator = new WordCounterSpliterator(sentence);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.println("Found " + sd.countWords(stream) + " words");
    }
}