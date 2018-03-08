# Machine Learning
  This is used for beginner to start learning machine-learning technology. It provides the both of basic and advance libraries.

# Libraries
  - __Linear Regression__
    Basically, It will be used for finding mathematical model to predict value in the future that base on previouse values. This requires dataset which is CSV format.
  - __Logistic Regression__
    It wiil be used for binary classificaion. 
  - __Neuron Network__
    Coming soon
  - __Deep Q Learning__
    Coming soon
# Example
  - Logistic Regression
  ```java
  /* Read dataset from inputs.csv file */
  LinkedList<Input> inputs = Input.fromFile("inputs.csv");
  
  /* Read expected value from ExpectedValue.csv */
  LinkedList<Double> ev = Input.expectedValueFromFile("ExpectedValue.csv");
  
  /* Initiaize coefficient (weight) and random value from 0 to 5 */
  Matrix weight = new Matrix(inputs.getFirst().getCol(), 1);
  weight.random(0, 5);

  /* Create Logistic Regression object */
  LogisticRegression LR = new LogisticRegression(inputs, ev, 0.001, 1000000, weight);
  
  /* Train model unitil it reach number of step */
  LR.train();
  
  /* Test model by using the inputs, but we recommend you should have dataset for testing the model particularly */
  LR.Test(inputs, ev);
  ```
  - Linear Regression
    Coming soon
  - Neuron Network
    Coming soon
