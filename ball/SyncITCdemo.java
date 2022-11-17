class Bank
{
	int balance = 10000;
	
	synchronized void withdraw(int amount)
	{
		if(balance < amount)
		{
			try
			{
				System.out.println("Waiting for withdrawal");
				wait();
			}
			catch(Exception e)
			{
				System.out.println("Error: Exception during wait");
			}
			
		}
		else
		{
			balance= balance - amount;
			System.out.println("Amount Withdrawn "+balance);
		}
	}
	
	synchronized void deposite(int amount)
	{
		balance= balance + amount;
		System.out.println("deposite completed "+balance);
		notify();
	}
}

class ThreadOne extends Thread
{
	Bank b;
	ThreadOne()
	{
		
	}
	
	ThreadOne(Bank b)
	{
		this.b=b;
	}
	public void run()
	{
		b.withdraw(15000);
	}
}

class ThreadTwo extends Thread
{
	Bank b;
	
	ThreadTwo()
	{
		
	}
	
	ThreadTwo(Bank b)
	{
		this.b=b;
	}
	public void run()
	{
		b.deposite(10000);
	}
}

class SyncITCdemo
{
	public static void main(String args[])
	{
		Bank bankObj = new Bank();
		
		ThreadOne t1= new ThreadOne(bankObj);
		
		ThreadTwo t2= new ThreadTwo(bankObj);
		
		t1.start();
		
		t2.start();
		
	}
}