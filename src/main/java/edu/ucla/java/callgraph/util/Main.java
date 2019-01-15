package edu.ucla.java.callgraph.util;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args){

        if(args.length != 2) {
            System.err.println("ERROR USAGE:\n java -jar /java-callgraph-util-1.0-SNAPSHOT.jar <callgraph-output> <mainClass>");
        }

        System.out.println("\n");

        String fileName = args[0];
        String mainClass = args[1];
        String originFormat = "%s:main(java.lang.String[])void";

        Map<String,Set<String>> adjList = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // process the line.
                if(line.startsWith("M:")){
                    String[] subParts = line.split(" ");
                    String src = subParts[0].substring(2);
                    String dest = subParts[1].substring(3);

                    Set<String> destSet = adjList.get(src);
                    if(destSet==null){
                        destSet = new HashSet<>();
                        adjList.put(src,destSet);
                    }
                    destSet.add(dest);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graph g = GraphUtil.buildGraph(adjList);

        String origin = String.format(originFormat,mainClass);

        String reachableMethods = g.
                getReachableMethods(origin).
                stream().
                map(s -> s+"\n"+transformMethodFormat(s)+"\n\n").
                reduce(new StringBuilder(),
                        StringBuilder::append,
                        StringBuilder::append).toString();

        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));
        try {
            log.write(reachableMethods);
            log.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String transformMethodFormat(String method){

        //replace all . with / and after that replace : with .
        String methodName = method.replace(".","/").replace(":",".").replace("<init>","\"<init>\"");

        //fix argument list
        String arguments = methodName.substring(methodName.indexOf('('),methodName.lastIndexOf(')'));
        arguments = processArgs(arguments.substring(1));
        String returnType = methodName.substring(methodName.lastIndexOf(')'));

        if(returnType.length()>1){
            returnType = processArgs(returnType.substring(1));
        }else{
            returnType = "";
        }

        String format = "%s(%s)%s";


        return String.format(format,methodName.substring(0,methodName.indexOf('(')),arguments,returnType);
    }

    private static String processArgs(String arguments){

        if(arguments.equals("void")) return "V";

        String[] argsArr =  arguments.split(",");

        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0;i< argsArr.length;i++){
            String arg = argsArr[i];

            //ends with [] => arr, => add [ to the start
            if(arg.endsWith("[]")){
                stringBuilder.append("[");
                arg = arg.substring(0,arg.length()-2);
            }

            // / exists implies the current argument is not a primitive type => add L to the start
            if(arg.contains("/")){
                stringBuilder.append("L");
                stringBuilder.append(arg);
            }else if(arg.startsWith("int")){
                stringBuilder.append("I");
            }else if(arg.startsWith("char")){
                stringBuilder.append("C");
            }else if(arg.startsWith(("long"))){
                stringBuilder.append("J");
            }

            if(i!=argsArr.length-1){
                stringBuilder.append(";");
            }
        }
        return stringBuilder.toString();
    }
}
