package edu.ucla.java.callgraph.util;
import org.junit.Test;

import static edu.ucla.java.callgraph.util.Main.transformMethodFormat;

public class MainTest {

    /**
     * ru.bioinf.rosalind.alignment.dynamicProgramming.CTEA:calcScoreMatrix(char[],char[])int[][]
     * ru/bioinf/rosalind/alignment/dynamicProgramming/CTEA.calcScoreMatrix:([C[C)[[I
     *
     * ru.bioinf.rosalind.common.NumberUtils:getMin(int,int,int)int
     * ru/bioinf/rosalind/common/NumberUtils.getMin:(III)I
     *
     *
     * ru.bioinf.rosalind.alignment.dynamicProgramming.CTEA_old:main(java.lang.String[])void
     * ru/bioinf/rosalind/alignment/dynamicProgramming/CTEA_old.main:([Ljava/lang/String;)V
     *
     * ru.bioinf.rosalind.alignment.dynamicProgramming.Edit:calcScoreMatrix(char[],char[])int[][]
     * ru/bioinf/rosalind/alignment/dynamicProgramming/Edit.calcScoreMatrix:([C[C)[[I
     *
     * ru.bioinf.rosalind.common.NumberUtils:getMin(int,int,int)int
     * ru/bioinf/rosalind/common/NumberUtils.getMin:(III)I
     *
     * org.apache.commons.cli.HelpFormatter:printHelp(java.io.PrintWriter,int,java.lang.String,java.lang.String,org.apache.commons.cli.Options,int,int,java.lang.String,boolean)void
     * org/apache/commons/cli/HelpFormatter.printHelp:(Ljava/io/PrintWriter;ILjava/lang/String;Ljava/lang/String;Lorg/apache/commons/cli/Options;IILjava/lang/String;Z)V
     *
     * org.apache.commons.cli.HelpFormatter:printHelp(int,java.lang.String,java.lang.String,org.apache.commons.cli.Options,java.lang.String,boolean)void
     * org/apache/commons/cli/HelpFormatter.printHelp:(ILjava/lang/String;Ljava/lang/String;Lorg/apache/commons/cli/Options;Ljava/lang/String;Z)V
     *
     * org.apache.commons.cli.HelpFormatter$OptionComparator:<init>()void
     * org/apache/commons/cli/HelpFormatter$OptionComparator."<init>":()V
     *
     * org.apache.commons.cli.Option:<init>(java.lang.String,java.lang.String,boolean,java.lang.String)void
     * org/apache/commons/cli/Option."<init>":(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V
     *
     * me.limebyte.rain.sound.Song:fade(javax.sound.sampled.FloatControl,double,float,float)void
     * me/limebyte/rain/sound/Song.fade:(Ljavax/sound/sampled/FloatControl;DFF)V
     * */
    @Test
    public void testInputOutput(){
        String[] input = new String[]{
                "ru.bioinf.rosalind.alignment.dynamicProgramming.CTEA:calcScoreMatrix(char[],char[])int[][]",
                "ru.bioinf.rosalind.common.NumberUtils:getMin(int,int,int)int",
                "ru.bioinf.rosalind.alignment.dynamicProgramming.CTEA_old:main(java.lang.String[])void",
                "ru.bioinf.rosalind.alignment.dynamicProgramming.Edit:calcScoreMatrix(char[],char[])int[][]",
                "ru.bioinf.rosalind.common.NumberUtils:getMin(int,int,int)int",
                "org.apache.commons.cli.HelpFormatter:printHelp(java.io.PrintWriter,int,java.lang.String,java.lang.String,org.apache.commons.cli.Options,int,int,java.lang.String,boolean)void",
                "org.apache.commons.cli.HelpFormatter:printHelp(int,java.lang.String,java.lang.String,org.apache.commons.cli.Options,java.lang.String,boolean)void",
                "org.apache.commons.cli.HelpFormatter$OptionComparator:<init>()void",
                "org.apache.commons.cli.Option:<init>(java.lang.String,java.lang.String,boolean,java.lang.String)void",
                "me.limebyte.rain.sound.Song:fade(javax.sound.sampled.FloatControl,double,float,float)void"
        };

        String[] output = new String[]{
                "ru/bioinf/rosalind/alignment/dynamicProgramming/CTEA.calcScoreMatrix:([C[C)[[I",
                "ru/bioinf/rosalind/common/NumberUtils.getMin:(III)I",
                "ru/bioinf/rosalind/alignment/dynamicProgramming/CTEA_old.main:([Ljava/lang/String;)V",
                "ru/bioinf/rosalind/alignment/dynamicProgramming/Edit.calcScoreMatrix:([C[C)[[I",
                "ru/bioinf/rosalind/common/NumberUtils.getMin:(III)I",
                "org/apache/commons/cli/HelpFormatter.printHelp:(Ljava/io/PrintWriter;ILjava/lang/String;Ljava/lang/String;Lorg/apache/commons/cli/Options;IILjava/lang/String;Z)V",
                "org/apache/commons/cli/HelpFormatter.printHelp:(ILjava/lang/String;Ljava/lang/String;Lorg/apache/commons/cli/Options;Ljava/lang/String;Z)V",
                "org/apache/commons/cli/HelpFormatter$OptionComparator.\"<init>\":()V",
                "org/apache/commons/cli/Option.\"<init>\":(Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;)V",
                "me/limebyte/rain/sound/Song.fade:(Ljavax/sound/sampled/FloatControl;DFF)V"
        };

        for(int i=0;i<input.length;i++){
            System.out.println(transformMethodFormat(input[i]));
            System.out.println(output[i]);
            assert (transformMethodFormat(input[i]).equals(output[i]));
        }
    }
}
