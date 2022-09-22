class main 
{
  public static void main(String[] args)
  {
    int temp = 1;
    int zeroCounter = 0;
    int[] input = new int[]{1, 2, 3, 4};
    int[] output = new int[input.length];
    for(int i = 0; i < input.length; i++)
    {
      if(input[i] != 0)
      {
      temp *= input[i];
      }
      else
      {
        zeroCounter++;
      }

    }
    for(int i = 0; i < input.length; i++)
    {
      if(input[i] == 0 && zeroCounter == 1)
      {
        output[i] = temp;
      }
      else if(input[i] != 0 && zeroCounter == 1)
      {
        output[i] = 0;
      }
      else if(zeroCounter > 1)
      {
        output[i] = 0;
      }
      else
      {
        output[i] = temp / input[i];
      }
      System.out.println(output[i]);
    }
  } 
}