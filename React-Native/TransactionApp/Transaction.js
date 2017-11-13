import React from 'react';
import {View, Text, StyleSheet, TouchableHighlight} from 'react-native';
//import {Navigator} from 'react-native-deprecated-custom-components'
//import {TransactionView} from './TransactionView.js'
export default class Transaction extends React.Component{
    id: number;
    category: string;
    type: string;
    value: number;
    pressStatus: boolean;

    constructor(id, category, type, value) {
        super();
        this.id = id;
        this.category = category;
        this.type = type;
        this.value = value;
        this.pressStatus= false;
    }

    _onPressButton(){
        this.pressStatus=true;

    }

    render(){
        return (
            <TouchableHighlight onPress={this._onPressButton}>
                <View style = {styles.tr}>
                    <Text> Transaction {this.id}</Text>
                    <Text> Category: {this.category}</Text>
                    <Text> Type:  {this.type}</Text>
                    <Text> Value: {this.value}</Text>
                </View>
            </TouchableHighlight>
        );
    }
}



const styles = StyleSheet.create({
    tr: {
        padding: 15,
        marginBottom: 5,
        backgroundColor: 'skyblue',
    },
    trPress: {
        padding: 15,
        marginBottom: 5,
        backgroundColor: 'red',
    },
});
