package MR;

import RM.*; 

public class Combine_Task_Execution { 
	public int block_no, counter;					// also map task number because for every one block one map task is launched
	public String thread_name;
	public MR_App_Master temp;
	public Thread t;
	public long non_local_exe_delay; 
	int in_mem_cache=100;					// in memory cache size in MB
	int mem_thresh=80;
	
	public Combine_Task_Execution(MR_App_Master temp, int block_no) throws InterruptedException{
		this.temp=temp;
		this.block_no=block_no;
		for(counter=4;counter<temp.Map_Output[0].length;counter++){ 
			Thread.sleep((long) ((temp.Map_Output[block_no][counter]/mem_thresh)*1000));		
			temp.Map_Output[block_no][counter]=temp.Map_Output[block_no][counter]*10;		
		}				
	}	
}
