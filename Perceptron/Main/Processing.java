package Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class Processing {
	
	public static Integer MAX_ITERATION=2000;
	public static Double learning_rate =0.1;
	double bias=1;
	public static Double globalerror=0.0;
	public static Integer spamcount=0,hamcount=0;
	public static Map<Integer,String> getpunc_table()
	{
		
		Map<Integer,String> punc=new Hashtable<>();
		punc.put(1,",");
		punc.put(2,"\\");
		punc.put(3,"/");
		punc.put(4,".");
		punc.put(5,"*");
		punc.put(6,"!");
		punc.put(7,"?");
		punc.put(8,"'");
		punc.put(9,":");
		punc.put(10,"-");
		punc.put(11,"#");
		punc.put(12,"%");
		punc.put(13,";");
		punc.put(14,"$");
		punc.put(15,"@");
		punc.put(16,"(");
		punc.put(17,")");
		punc.put(18,"_");
		punc.put(19,"+");
		punc.put(20,"<");
		punc.put(21,">");
		punc.put(22,"\"");
		punc.put(23,"&");
		punc.put(24, "1");
		punc.put(25, "2");
		
		return punc;
	}

	public void train(Map<String,Map<String,Integer>> spam_dict,Map<String,Map<String,Integer>> ham_dict)
	{
		//assigning weights to each word in spam;
		int iter=0;
	//	System.out.println("global vocab size"+Readfile.global_vocab.size());
		/*for(String word:Readfile.global_vocab.keySet())
		{
			double weight=(Math.random()*(2)) ;
			Readfile.weight_vector.put(word, weight);
		}
		*/
		//Readfile.weight_vector
		for(String word:Readfile.spam_vocab.keySet())
		{
			Readfile.weight_vector.put(word, Math.random());
			
		}
		//System.out.println("size:"+Readfile.global_vocab.size());
		for(String word:Readfile.ham_vocab.keySet())
		{
			
			if(!Readfile.weight_vector.containsKey(word))
			{
				Readfile.weight_vector.put(word, Math.random());
			}
		}
		
		do{
			//calculting output for each ham example
			for(String file:ham_dict.keySet() )
			{
				Map<String,Integer> words_and_count=ham_dict.get(file);
				double td=-1;
			
			//calculate the predicted output 
				double predicted_output=cal_output(words_and_count);
				//for spam the actual output is +1
				double error=td-predicted_output;
				if(error!=0)
				{
					for(String word:words_and_count.keySet())
					{
						int count=words_and_count.get(word);
						double weight=Readfile.weight_vector.get(word);
						double newweight=learning_rate *error * count;
						double weight_update=weight+newweight;
						Readfile.weight_vector.put(word, weight_update);
					}
					bias=bias+learning_rate * error;
				//	System.out.println("bias update in spam:"+bias);
					globalerror+=error*error;
				}
			//	weight_vector.get(
			}
			for(String file:spam_dict.keySet() )
			{
				double td=1;
				Map<String,Integer> words_and_count=spam_dict.get(file);
			//	bias=Math.random()*(0-1);
				double predicted_output=cal_output(words_and_count);
				//for spam the actual output is -1
				double error=td-predicted_output;
				if(error!=0)
				{
					for(String word:words_and_count.keySet())
					{
						int count=words_and_count.get(word);
						double weight=Readfile.weight_vector.get(word);
						double newweight=learning_rate*error*count;
						double weight_update=weight+newweight;
						Readfile.weight_vector.put(word, weight_update);
					}
					bias=bias+learning_rate*error;
				//	System.out.println("bias update in ham:"+bias);
					globalerror+=error*error;
				}
			//	weight_vector.get(
			}
			iter++;
		}while(iter<MAX_ITERATION);
		//System.out.println("global error:"+globalerror);
		
			
	}

	private double cal_output(Map<String, Integer> words_and_count) 
	{
		//double bias=1;
		double sum=bias;
		for(String word:words_and_count.keySet())
		{
			int count=words_and_count.get(word);
			sum=sum+Readfile.weight_vector.get(word)*count;
		}
		if(sum>0){
			return 1;
		}
		else return -1;
		
	}
	
	
		
	
	

}
