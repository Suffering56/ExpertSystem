package expertsystem;

class Answer {
    private final String answer1;
    private final String answer2;
    private final String answer3;
    private final int trueAnswerNumber;

    public Answer (String answer1Text, String answer2Text, 
            String answer3Text, int trueAnswerNumber) {
        this.answer1 = answer1Text;
        this.answer2 = answer2Text;
        this.answer3 = answer3Text;
        this.trueAnswerNumber = trueAnswerNumber;
    }

    public int getTrueAnswerNumber() {
        return trueAnswerNumber;
    }

    public String getAnswer(int answerNumber) {
        String result = "";
        switch (answerNumber) {
            case 1: result = answer1; break;
            case 2: result = answer2; break;
            case 3: result = answer3; break;
        }        
        return result;
    }
}
