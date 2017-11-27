import React from 'react';
import {View, Text, TextInput, StyleSheet} from 'react-native';
import Transaction from "./Transaction";

export class TransactionView extends React.Component{
    static navigationOptions =  ({navigation}) => ({
        title: navigation.state.params.name,
    });
    transaction: Transaction;

    constructor(tr) {
        super();
        this.transaction = tr;
    }
    render() {
        return (
            <View style = {styles.container}>
                <Text>Category</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({text})}
                    value={this.transaction.category}
                />
                <Text>Type</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({text})}
                    value={this.transaction.type}
                />
                <Text>Value</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(text) => this.setState({text})}
                    value={this.transaction.value}
                />
            </View>
        );
    }
}
const styles = StyleSheet.create({
    container: {
        marginTop: 25,
        flex: 1,
    },
});