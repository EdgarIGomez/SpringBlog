package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String guess(@PathVariable int n, Model model){
        int counter = 0;
        ArrayList<Integer> rolls  = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            int diceRoll = 1 + (int)(Math.random() * 6);
            if(n == diceRoll){
                counter++;
            }
            rolls.add(diceRoll);
        }

        String message = "The dice rolled: ";
        String results = "You guessed " + n + " which you got  right " + counter + " times!";
        model.addAttribute("message", message);
        model.addAttribute("results", results);
        model.addAttribute("rolls", rolls);
        return "roll-dice";
    }
}
