package com.anish.syrus2020;

public class QuestionLibrary {
    private String mQuestion[]={
            "Q. Near a pedestrian crossing, when the pedestrians are waiting to cross  the road, you should ...",
            "Q. You are approaching a arrow bridge, another vehicle is about to enter the bridge from opposite side you should",
            "Q. When a vehicle is involved in an accident causing injury to any person",
            "Q. On a road designated as one way",
            "Q. You can overtake a vehicle in front ...",
            "Q. When a vehicle approaches an unguarded railway level crossing, before crossing it, the driver shall",
            "Q. How can you distinguish a transport vehicle?",
            "Q. Validity of learners license",
            "Q. In a road without footpath, the pedestrians ...",
            "Q. Free express should be given to the following types of vehicles"
    };
    private String mchoices[][]={
            {"Sound horn and proceed","Slow down, sound horn and pass","Stop the vehicle and wait till the pedestrians cross the road and then proceed"},
            {"Increase the speed and try to cross the bridge as fast as possible","Put on the headlight and pass the bridge","Wait till the other vehicle crosses the bridge and then proceed"},
            {"Take the vehicle to the nearest police station and report the accident","Stop the vehicle and report to the police station","Take all the reasonable steps to secure medical attention to the injured and report to the nearest police station within 24 hours"},
            {"Parking os prohibited","Overtaking is prohibited","Should not drive in reverse gear"},
            {"Through the right side of the vehicle","Through the left side","Through the left side, if the road is wide"},
            {"Stop the vehicle on the left side of the road , get down from the vehicle, go to the railway track, and ensure that no train or trolley is coming from the either side","Sound horn and cross the track as fast as possible"},
            {"By looking at the tyre size","By color of the vehicle","By looking at the number plate of the vehicle"},
            {"Till the driver license is obtained","6 Months","30 Days"},
            {"Should walk on the right side of the road","Should walk on the left side of the road","May walk on either side of the road"},
            {"Police Vehicles","Ambulance and fire service vehicles","Express, Super Express buses"}
    };
    private int noChoices[]={
            3,
            3,
            3,
            3,
            3,
            2,
            3,
            3,
            3,
            3
    };


    private String mCorrectAnswer[]={
            "Stop the vehicle and wait till the pedestrians cross the road and then proceed",
            "Wait till the other vehicle crosses the bridge and then proceed",
            "Take all the reasonable steps to secure medical attention to the injured and report to the nearest police station within 24 hours",
            "Should not drive in reverse gear",
            "Through the right side of the vehicle",
            "Stop the vehicle on the left side of the road , get down from the vehicle, go to the railway track, and ensure that no train or trolley is coming from the either side",
            "By looking at the number plate of the vehicle",
            "6 Months",
            "Should walk on the right side of the road",
            "Ambulance and fire service vehicles"
    };

    public String getQuestion(int a){
        String question=mQuestion[a];
        return question;
    }

    public int getNoOptions(int a)
    {
        return noChoices[a];
    }



    public String getChoice1(int a){
        String choice0=mchoices[a][0];
        return choice0;
    }

    public String getChoice2(int a){
        String choice1=mchoices[a][1];
        return choice1;
    }


    public String getChoice3(int a){
        String choice1=mchoices[a][2];
        return choice1;
    }


    public String getChoice4(int a){
        String choice1=mchoices[a][3];
        return choice1;
    }


    public String getCorrectAnswer(int a){
        String answer=mCorrectAnswer[a];
        return answer;
    }
}
