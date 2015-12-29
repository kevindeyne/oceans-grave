var soundManager;
var playerStunned = false;

$(function(){		
	var roundManager = new RoundManager();
	soundManager = new SoundManager();
	soundManager.soundBattle();
	
	var timeout;
	/*SKILLS DESCRIPTION*/
	$(".skill").hover(function(){
		if(typeof(timeoutSkill) != "undefined"){
			clearTimeout(timeoutSkill);
		}
		$("#skill-description i").show();
		$("#skill-description strong").next().show();
		$("#skill-description").fadeTo(100, 1);
		var name = $(this).attr("skill-name");
		var range = $(this).attr("skill-range");
		var type = $(this).attr("skill-type");
		var description = $(this).attr("skill-description");
		var stats = $(this).attr("skill-stats");
		
		$("#skill-description #descr-skill-name").text(name);
		$("#skill-description #descr-skill-range").text(range);
		$("#skill-description #descr-skill-type").text(type);
		$("#skill-description #descr-skill-description").text(description);
		$("#skill-description #descr-skill-stats").text(stats);
	},
	function(){
		timeoutSkill = setTimeout(function(){
			$("#skill-description").fadeTo(100, 0);
		}, 500);
	});
	
	/*STATS DESCRIPTION*/
	$(".stats .stats-icon").hover(function(){
		if(!$(this).hasClass("invisible")){
			if(typeof(timeoutStats) != "undefined"){
				clearTimeout(timeoutStats);
			}
			
			$("#skill-description i").hide();
			$("#skill-description strong").next().hide();
			$("#skill-description").fadeTo(100, 1);
			var name = $(this).attr("attr-name");
			var description = $(this).attr("attr-description");
			var stats = "Current value: " + $(this).attr("attr-skills");
			
			$("#skill-description #descr-skill-name").text(name);
			$("#skill-description #descr-skill-description").text(description);
			$("#skill-description #descr-skill-stats").text(stats);
		}
	},
	function(){
		timeoutStats = setTimeout(function(){
			$("#skill-description").fadeTo(100, 0);
		}, 500);
	});
	
	/*HEALTH CIRCLE INITS*/
	 $('#player-circle').circleProgress({
	     value: 1,
	     fill: { gradient: [['#0681c4', .25], ['#4ac5f8', .75]], gradientAngle: Math.PI / 4 }
	 }).on('circle-animation-progress', function(event, progress, stepValue) {
	     $(this).find('.current-health').text(String((stepValue*100).toFixed(2)).substr(0,String((stepValue*100).toFixed(2)).indexOf(".")));
	 });
	 
	 $('#enemy-circle').circleProgress({
	     value: 1,
	     fill: { gradient: [['#c40606', .25], ['#f84a4a', .75]], gradientAngle: Math.PI / 4 }
	 }).on('circle-animation-progress', function(event, progress, stepValue) {
	     $(this).find('.current-health').text(String((stepValue*100).toFixed(2)).substr(0,String((stepValue*100).toFixed(2)).indexOf(".")));
	 });
});
	
/**
 * @name RoundManager, manages the rounds of the combat
 * @author Kevin Deyne
 *
 * Basic usage:
 * var someFunction = new SomeFunction();
 * someFunction.init();
 *
 * additionally you can use methods like someFunction.methodName();
 *
 * Advanced usage:
 * var someFunction = new SomeFunction({
 *      "additionalOption": "thatCanOvervriteDefaults"
 * });
 */
function RoundManager(opts) {
    //assign _root and config private variables
    var _root = this;
    var _cfg = $.extend({
        "skillButtons": $(".skill")
    }, opts);

    _bindUIActions = function() {
		//bind actions to skill buttons
        _cfg.skillButtons.click(function (){   
        	if(!$(this).hasClass("disabled") && !$(this).hasClass("cooldown")){
	        	_markRoundAsEnemyTurn();
	        	
	        	var abilityId = $(this).attr("id");
	    		$.getJSON( abilityURL.replace(abilityParam, abilityId), function( data ) {
	    			_abilityEffect(data);
	    			_root.enemyRound(data.round);
	    		});
        	}
		});
    }
    
    _markRoundAsEnemyTurn = function() {
    	_cfg.skillButtons.addClass("disabled");
    	$("#round-info").text(messages.turnEnemy);
    }
    
    _markRoundAsPlayerTurn = function() {    	
    	_cfg.skillButtons.removeClass("disabled");
    	$("#round-info").text(messages.turnPlayer);
    }
    
    _abilityEffect = function(data) {
    	$("#enemy-info .flaticon-scorched").parent().attr("class", "stats-icon " + ((data.enemyChanges.scorched) ? "debuffed" : "invisible"));
    	$("#enemy-info .flaticon-stunned").parent().attr("class", "stats-icon " + ((data.enemyChanges.stunned) ? "debuffed" : "invisible"));
    	
    	_editActorHP("#enemy-info", parseInt(data.enemyChanges.health), false);
    	_editActorHP("#player-info", parseInt(data.playerChanges.health), false);
    	
    	$("#enemy-info .flaticon-accuracy").parent().attr("attr-skills", data.enemyChanges.acc);
    	$("#enemy-info .flaticon-defense").parent().attr("attr-skills", data.enemyChanges.def);
    	$("#player-info .flaticon-accuracy").parent().attr("attr-skills", data.playerChanges.acc);
    	$("#player-info .flaticon-defense").parent().attr("attr-skills", data.playerChanges.def);//
    	
    	_abilitiesOnCooldown(data);
    	
    	_buildBuffLevels();
    	_buildRecentAttackMessage(data);   	
    }
    
	_editActorHP = function(divID, hpChange, enemyRound) {
		if(0 != hpChange){
			var currentHP = parseInt($(divID + " .current-health").text());
			var maxHP = 100;
			currentHP += hpChange;
			
			var actor = "Enemy";
			if(-1 !== divID.indexOf("player")){ actor = "Player"; }
			
			var healed = "healed";
			if(hpChange < 0){ healed = "hit"; }
			
			if(enemyRound){
				actor = "Enemy round: " + actor;
			}
			
			_writeBattleLog(actor + " got " + healed + " for " + Math.abs(hpChange) + " points");
			
			if(currentHP < 0){ currentHP = 0; }		
			if(currentHP > maxHP ){ currentHP = maxHP; }
			
			$(divID + " .current-health").text(currentHP);
			
			if(-1 !== divID.indexOf("player")){ 
				$("#player-circle").circleProgress('value', currentHP/maxHP);
			} else{
				$("#enemy-circle").circleProgress('value', currentHP/maxHP);
			}
		}
	}
	
	
	_abilitiesOnCooldown = function(data) {
		var playerAbilities = data.playerChanges.abilitiesOnCooldown;
		if(null != playerAbilities){     		    	
	    	for(var abilityIndex in playerAbilities){	    		
	    		var abilityId = "#" + abilityIndex;
	    		if(playerAbilities[abilityIndex] <= 1){
	    			$(abilityId).removeClass("cooldown");
	    		} else {
	    			$(abilityId).addClass("cooldown");
	    		}	    		
	    	}
		}
    }
	
	_writeBattleLog = function(log) {
		var paragraph = $("<p />").text(log);
		$("#battle-log").prepend(paragraph);
    }
		
	//checks if any of the actors has reached 0 health
	_checkIfBattleIsOver = function() {
		var isOver = false;
		$(".current-health").each(function(){
			if(0 == parseInt($(this).text())){
				isOver = true;
			}
		});
        return isOver;
    }
	
	_battleOverSequence = function() {
        alert("done!");
    }
	
	_buildBuffLevels = function() {
		_buildBuffLevel("#enemy-info .flaticon-accuracy");
		_buildBuffLevel("#enemy-info .flaticon-defense");
		_buildBuffLevel("#player-info .flaticon-accuracy");
		_buildBuffLevel("#player-info .flaticon-defense");
    }
	
	_buildRecentAttackMessage = function(data) {		
		var spanPlayer = "<span class='player-highlight'>Player</span>";
		var spanEnemy = "<span class='enemy-highlight'>Enemy</span>";
		
		$("#recent-attack-ability").html(data.recentAttackAbility.replace("Player", spanPlayer).replace("Enemy", spanEnemy)).fadeTo(10, 1);
		$("#recent-attack-damage").html(data.recentAttackDamage.replace("Player", spanPlayer).replace("Enemy", spanEnemy)).fadeTo(10, 1);
		$("#recent-attack-buffs").html(data.recentAttackBuffs.replace("Player", spanPlayer).replace("Enemy", spanEnemy)).fadeTo(10, 1);
				
		timeoutMessage = setTimeout(function(){
			$("#recent-attack-ability").fadeTo(200, 0);
			$("#recent-attack-damage").fadeTo(200, 0);
			$("#recent-attack-buffs").fadeTo(200, 0);
		}, 1500);	
    }

	_buildBuffLevel = function(selector) {
		var acc = $(selector).parent().attr("attr-skills");
		if(-1 <= acc && 1 >= acc){
			$(selector).parent().attr("class", "stats-icon normal");
		} else if(-1 > acc){
			$(selector).parent().attr("class", "stats-icon debuffed");
		} else {
			$(selector).parent().attr("class", "stats-icon buffed");
		}
    }
	
    this.enemyRound = function(round) {
    	setTimeout(function(){    		
    		$.getJSON( enemyURL.replace(roundParam, round), function( data ) {
    			var isOver = _checkIfBattleIsOver();
        		if(!isOver){
        			setTimeout(function(){
        				$("#player-info .flaticon-scorched").parent().attr("class", "stats-icon " + ((data.playerChanges.scorched) ? "debuffed" : "invisible"));
        		    	$("#player-info .flaticon-stunned").parent().attr("class", "stats-icon " + ((data.playerChanges.stunned) ? "debuffed" : "invisible"));
        		    	
        		    	_editActorHP("#enemy-info", parseInt(data.enemyChanges.health), false);
        		    	_editActorHP("#player-info", parseInt(data.playerChanges.health), false);
        		    	
        		    	$("#enemy-info .flaticon-accuracy").parent().attr("attr-skills", data.enemyChanges.acc);
        		    	$("#enemy-info .flaticon-defense").parent().attr("attr-skills", data.enemyChanges.def);
        		    	$("#player-info .flaticon-accuracy").parent().attr("attr-skills", data.playerChanges.acc);
        		    	$("#player-info .flaticon-defense").parent().attr("attr-skills", data.playerChanges.def);
        		    	
        		    	_abilitiesOnCooldown(data);
        		    	        		    	        		    	
        		    	_buildBuffLevels();
        		    	_buildRecentAttackMessage(data);
        		    	
        		    	playerStunned = data.playerChanges.stunned;
        		    	
        				setTimeout(function(){
        					if(_checkIfBattleIsOver()){
        						_battleOverSequence();
        					} else {
        						_markRoundAsPlayerTurn();
        						
        				    	if(playerStunned){
        				    		if(null != nonCombatAbilities){
        				    			$("#skillset div.skill").addClass("disabled");
        				    	    	for(var abilityIndex in nonCombatAbilities){
        				    	    		var abilityId = "#" + nonCombatAbilities[abilityIndex];        
        				    	    		$(abilityId).removeClass("disabled");
        				    	    	}
        				    		}
        				    	} else {
        				    		$("#skillset div.skill").removeClass("disabled");
        				    	}
        					}
        				}, 1750);
        			}, 1100);
        		} else {
        			_battleOverSequence();
        		}   			
    		});
		}, 1000);
    }
    
    /* INITIALIZE */
    var init = function() {
      _bindUIActions();
      return _root;
    }
    
    return init();
}