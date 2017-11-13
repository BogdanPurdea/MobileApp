import React from 'react';
import { StyleSheet, Text, View, FlatList} from 'react-native';
import Transaction from './Transaction.js'
import {TransactionView} from "./TransactionView";

t1 = new Transaction(1,'Salary','Income',5000);
t2 = new Transaction(2,'School','Expense',1000);
t3 = new Transaction(3,'Food','Expense',100);

const transactions = [t1,t2,t3];
const extractKey = ({id}) => id;

export default class TransactionList extends React.Component {

    render() {
        return (
            <View style = {styles.container}>
                <Text>Transactions</Text>
                <FlatList
                    data={transactions}
                    renderItem={({item}) => item.render()}
                    keyExtractor={extractKey}
                />
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        marginTop: 30,
        flex: 1,
    },
});
/*const Stack = StackNavigator({
    Home: {
        screen: TransactionList,
    },
});*/
/*import React, { Component } from 'react';
import {AppRegistry, Navigator,} from 'react-native';

import TransactionList from './TransactionList.js';
import {TransactionView} from "./TransactionView";

class App extends Component {
    constructor (props) {
        super(props);
        this.state = { screen: 2 };
        this.state.transfer = {};
    }

    _renderScene = ( route, navigator ) => {
        _navigator = navigator;
        switch ( route.id ) {
            case 'TransactionList':
                return(<TransactionList navigator={navigator} title='TransactionList' />);
                break;
            case 'TransactionView':
                return(<TransactionView navigator={navigator} title="TransactionView" />);
                break;
            // case 'Card':
            //   return <Card navigator={navigator} title=''/>
            // break;
        }
    };

    render = () => {
        return (
            <Navigator
                initialRoute={{
                    id: 'TransactionList'
                }}
                renderScene={
                    this._renderScene
                }
            />
        );
    }
}
AppRegistry.registerComponent('App', () => App);*/