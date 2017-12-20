import React from 'react';

import {View, Text, StyleSheet, TouchableOpacity} from 'react-native';
export default class Transaction extends React.Component{

    onPress = () => {
        this.props.onPressItem(this.props.id);
    };

    render(){
        return (
            <TouchableOpacity onPress={this.onPress}>
                <View style = {styles.tr}>
                    <Text> Transaction {this.props.id}</Text>
                    <Text> Category: {this.props.category}</Text>
                    <Text> Type:  {this.props.type}</Text>
                    <Text> Value: {this.props.value}</Text>
                </View>
            </TouchableOpacity>
        );
    }
}



export const TransactionSchema = {
    name: 'Transaction',
    primaryKey: 'id',
    properties: {
        id:  'int',
        category: 'string',
        type: 'string',
        value: 'int'
    }
};

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
