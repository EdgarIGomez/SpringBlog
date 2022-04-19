package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice(){
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String guess(@PathVariable int n, Model model){
        int diceRoll = 1 + (int)(Math.random() * 6);
        String message;
        if(n == diceRoll){
            message = "The dice rolled a " + diceRoll + " you guessed " + n + " which is correct congratulations!";
        }else if(n > diceRoll){
            message = "The dice rolled a " + diceRoll + " you guessed " + n + " which is too high try again next time!";
        }else{
            message = "The dice rolled a " + diceRoll + " you guessed " + n + " which is too low try again next time!";
        }
        model.addAttribute("message", message);
        return "dice-roll-results";
    }
}
