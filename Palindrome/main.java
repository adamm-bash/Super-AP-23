class main 
{
  public static void main(String[] args)
  {
    int input = -121;
    System.out.println(isPalindrome(input));  
  }

  public static boolean isPalindrome(int input)
  {
    if(input < 0)
    {
      return false;
    }
    int original = input;
    int reverse = 0;
    int remainder;
    boolean isPalindrome;
  
    while (original != 0) {
      remainder = original % 10;
      reverse = reverse * 10 + remainder;
      original /= 10;
    }

    if (input == reverse) {
      return true;
    }
    else {
      return false;
    }
  }
}