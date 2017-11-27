import React from 'react';

import {TransactionView} from "./TransactionView";
import { StackNavigator} from 'react-navigation';
import {TransactionList} from "./TransactionList";
import {InputForm} from "./InputForm";

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
});
export default App;