package expertsystem;

class Test {
    private String question;
    private Answer answer;

    public Test(String question, Answer answer) {
        this.question = question;
        this.answer = answer;
    }   

    public String getQuestion() {
        return question;
    }

    public String getAnswer(int answerNumber) {
        return answer.getAnswer(answerNumber);
    }
    
    public int getTrueAnswerNumber() {
        return answer.getTrueAnswerNumber();
    }
}
