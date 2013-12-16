public class Main {
    private static int start_value = 10;	//initialise model, which in turn initialises view

    public static void main(String[] args) {

        Model myModel 	= new Model();
        View myView 	= new View();

        myModel.addObserver(myView);

        Controller myController = new Controller();
        myController.addModel(myModel);
        myController.addView(myView);
        myController.initModel(start_value);

        myView.addController(myController);
    }
}
