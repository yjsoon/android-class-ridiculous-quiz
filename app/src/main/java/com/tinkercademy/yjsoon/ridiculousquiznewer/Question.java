package com.tinkercademy.yjsoon.ridiculousquiznewer;

public class Question {

    private int mStatement;
    private boolean mStatementTruth;

    public Question(int statement, boolean statementTruth) {
        mStatement = statement;
        mStatementTruth = statementTruth;
    }

    public int getStatement() {
        return mStatement;
    }

    public void setStatement(int statement) {
        mStatement = statement;
    }

    public boolean isStatementTruth() {
        return mStatementTruth;
    }

    public void setStatementTruth(boolean statementTruth) {
        mStatementTruth = statementTruth;
    }
}


