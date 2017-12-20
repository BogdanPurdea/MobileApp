import React from 'react';
import {View, Text, TextInput, StyleSheet, Button} from 'react-native';


export class DeleteTransaction extends React.Component{
    static navigationOptions =  ({navigation}) => ({
        title: 'Delete',
    });
    realm = this.props.navigation.state.params.realm;
    remove(){
        let transaction = this.realm.objectForPrimaryKey('Transaction', parseInt(this.state.id));
        this.realm.write(() => {
            this.realm.delete(transaction);
        });
        this.props.navigation.navigate('List');
    }
    render() {
        return (
            <View style = {styles.container}>
                <Text> ID</Text>
                <TextInput
                    style={{height: 40, borderColor: 'gray', borderWidth: 1}}
                    onChangeText={(id) => this.setState({id})}
                />

                <Button
                    onPress={() => this.remove()}
                    title="Submit"
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