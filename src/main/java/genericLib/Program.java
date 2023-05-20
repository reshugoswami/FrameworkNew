package genericLib;

public class Program
{
	public static void main(String[] args) 
	{
			for(int i=1;i<=4;i++)
			{
				int l=1;
				int k=2;
				for(int j=1;j<=4;j++)
				{
					if(i==1 || i==3)
					{
						System.out.print(l);
						l=l*2;
						
					}
				
				else
				{
						System.out.print(k);
						k=k+2;
				}
					
				}
				
				System.out.println(" ");
			}
		
		
	}
	
}
