package Main;

import LR.Convert2D;
import LR.ProcessingLR;
import LR.TestLR;
import LR.TrainLRnew;

public class Hw3_Main {

	
	
	public static void main(String args[])
	{
		
		
		if(args.length==5){
			System.out.println("----Perceptron----");
		Readfile rf=new Readfile();
		String SWfile=args[4];   //stopword file
		rf.createdict(args[0],args[1]);   //training data of spam and ham
		Processing pr=new Processing();
		pr.train(Readfile.spam_dict,Readfile.ham_dict);
		WritetoCSV w=new WritetoCSV();
		w.write();
		//pr.test_ham_spam(Readfile.test_spam_dict,Readfile.test_ham_dict);
		TestPerceptron t=new TestPerceptron();
		t.testing(args[2], args[3]);   //test data of spam and ham
		//removing stop words and then training and testing
		RemoveStopwords rw=new RemoveStopwords();
		rw.remove_sw(SWfile);
		pr.train(Readfile.spam_dict,Readfile.ham_dict);
		t.testing_sw(args[2], args[3], SWfile);
		
		
		}
		
	}
}
