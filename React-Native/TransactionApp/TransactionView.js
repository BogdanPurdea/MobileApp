import React from 'react';
import {View, Text, TextInput, StyleSheet, Button, Picker} from 'react-native';
import Transaction from "./Transaction";


export class TransactionView extends React.Component{
    static navigationOptions =  ({navigation}) => ({
        title: 'Transaction ' + navigation.state.params.transactionId,
    });

    realm = this.props.navigation.state.params.realm;
    transaction = this.realm.objectForPrimaryKey('Transaction', this.props.navigation.state.params.transactionId);
    state = {type: this.transaction.type}
    update(){
        this.realm.write(() => {
            if(this.state.category!=null)
                this.transaction.category = this.state.category.toString();
            if(this.state.type!=null)
                this.transaction.type = this.state.type.toString();
            if(this.state.value!=null)
                this.transaction.value = parseInt(this.state.value);
        });
        this.props.navigation.navigate('List');
    }
    render() {
        return (
            <View style = {styles.container}>
                <Text> Category</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    defaultValue={this.transaction.category}
                    onChangeText={(category) => this.setState({category})}
                />
                <Text> Type</Text>
                <Picker
                    selectedValue={this.state.type}
                    onValueChange={(type) => this.setState({type: type})}>
                    <Picker.Item label="Expense" value="Expense" />
                    <Picker.Item label="Income" value="Income" />
                </Picker>
                <Text> Value</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    defaultValue={this.transaction.value.toString()}
                    onChangeText={(value) => this.setState({value})}
                />
                <Button
                    onPress={() => this.update()}
                    title="Update"
                    color="#841584"
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