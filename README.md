# Machine Learning
  This is used for beginner to start learning machine-learning technology. It provides the both of basic and advance libraries.

# Libraries
  - __Linear Regression:__ 
    Basically, It will be used for finding mathematical model to predict value in the future that base on previouse values. This requires dataset which is CSV format.
  - __Logistic Regression:__
    It will be used for binary classificaion. 
  - __Neuron Network:__
    Coming soon
  - __Deep Q Learning:__
    Coming soon
# Cost fucntion
  ![](/docs/linear-regression/cost.png)
# Example
  - Linear Regression
  ```java
  /* Prepare parameter */
  double learningRate = 0.0001;
  int numOfStep = 10000;

  /* Read dataset from inputs.csv file */
  LinkedList<Dataset> datasets = Dataset.fromFile("src\\com\\github\\pepsi7959\\SupervisedLearning\\inputs.csv");

  /* Read expected value from ExpectedValue.csv */
  LinkedList<Double> ev = Dataset
      .expectedValueFromFile("src\\com\\github\\pepsi7959\\SupervisedLearning\\ExpectedValue.csv");

  /* Initialize coefficient (weight) and random value from 0 to 5 */
  Matrix weight = new Matrix(datasets.getFirst().getCol(), 1);
  weight.random(0, 5);

  /* Create Linear Regression object */
  LinearRegression LR = new LinearRegression(datasets, ev, learningRate, numOfStep, weight);

  /* Train model until it reach number of step */
  LR.train();

  /*
   * Test model by using the inputs, but we recommend you should have dataset for
   * testing the model particularly
   */
  LR.test(datasets, ev);
  ```
  - Logistic Regression
  ```java
  /* Prepare parameter */
  double learningRate = 0.001;
  int numOfStep = 1000000;

  /* Read dataset from inputs.csv file */
  LinkedList<Dataset> datasets = Dataset
      .fromFile("src\\com\\github\\pepsi7959\\UnsupervisedLearning\\inputs.csv");

  /* Read expected value from ExpectedValue.csv */
  LinkedList<Double> ev = Dataset
      .expectedValueFromFile("src\\com\\github\\pepsi7959\\UnsupervisedLearning\\ExpectedValue.csv");

  /* Initialize coefficient (weight) and random value from 0 to 5 */
  Matrix weight = new Matrix(datasets.getFirst().getCol(), 1);
  weight.random(0, 5);

  /* Create Logistic Regression object */
  LogisticRegression LR = new LogisticRegression(datasets, ev, learningRate, numOfStep, weight);

  /* Train model until it reach number of step */
  LR.train();

  /*
   * Test model by using the inputs, but we recommend you should have dataset for
   * testing the model particularly
   */
  LR.Test(datasets, ev);
  ```

  - Neuron Network
    Coming soon
