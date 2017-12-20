/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React from 'react';

import {TransactionView} from "./TransactionView";
import { StackNavigator} from 'react-navigation';
import {TransactionList} from "./TransactionList";
import {InputForm} from "./InputForm";
import {AddTransaction} from "./AddTransaction";
import {DeleteTransaction} from "./DeleteTransaction";
//import {DisplayChart} from "./DisplayChart";

const App = StackNavigator({
    Home: {
        screen: InputForm
    },
    List: {
        screen: TransactionList
    },
    Item:{
        screen: TransactionView
    },
    Add:{
        screen: AddTransaction
    },
    Delete: {
        screen: DeleteTransaction
    },
    /*Chart: {
        screen: DisplayChart
    }*/
});
export default App;
