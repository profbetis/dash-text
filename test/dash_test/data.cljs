(ns dash-test.data)

(def basic-state
   {:lists {:Soon {:name "Soon"
                   :id :Soon
                   :tasks {:Get_this_working
                           {:title "Get this working" :completed true :date "2015 / 01 / 12" :timedue "6pm"}
                           :Eat_dinner
                           {:title "Eat dinner" :completed false :date "Everyday" :timedue "8pm"}
                           :Bros_Grumpout
                           {:title "Bros Grumpout" :completed true :date "Everyday" :timedue "All the time"}}}

            :Later {:name "Later"
                    :id :Later
                    :tasks {:Wake_up
                            {:title "Wake up" :completed false :date "Everyday" :timedue "8am-ISH"}
                            :Eat_breakfast
                            {:title "Eat breakfast" :completed false :date "2015 / 01 / 12" :timedue "6pm"}
                            :Render_some_cool_stuff
                            {:title "Render some cool stuff" :completed false :date "Any day" :timedue "Any time"}
                            :Play_some_games
                            {:title "Play some games" :completed false :date "Any day" :timedue "After work"}}}

            :Future {:name "Future"
                     :id :Future
                     :tasks {:Be_successful_as_fuck
                             {:title "Be successful as fuck" :completed false :date "Someday" :timedue "n/a"}
                             :Complete_Mindfork
                             {:title "Complete Mindfork" :completed false :date "Someday" :timedue "n/a"}
                             :Complete_Phoenix_Engine
                             {:title "Complete Phoenix Engine" :completed false :date "Someday" :timedue "n/a"}
                             :Complete_Dwarf_Game
                             {:title "Complete Dwarf Game" :completed false :date "Someday" :timedue "n/a"}
                             :Die_happy
                             {:title "Die happy" :completed false :date "On your last day" :timedue "At the end of your time"}}}
            }
    :user "Mind Forker"
    })
